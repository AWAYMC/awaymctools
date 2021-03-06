package net.argania.core;

import static net.argania.core.store.StoreMode.MYSQL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.argania.core.cmd.CMDItemShop;
import net.argania.core.commands.CombatCommand;
import net.argania.core.commands.RevoGuildCommand;
import net.argania.core.commands.SubCommand;
import net.argania.core.commands.guild.GuildAdminCommand;
import net.argania.core.commands.guild.GuildCommand;
import net.argania.core.commands.ranking.RankingAdminCommand;
import net.argania.core.commands.ranking.RankingCommand;
import net.argania.core.commands.ranking.TopCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.data.TabScheme;
import net.argania.core.listeners.ActionsListener;
import net.argania.core.listeners.AsyncChatListener;
import net.argania.core.listeners.CombatListener;
import net.argania.core.listeners.DamageListener;
import net.argania.core.listeners.DeathListener;
import net.argania.core.listeners.ExplodeListener;
import net.argania.core.listeners.FightCommandsListener;
import net.argania.core.listeners.GuildCommandsListener;
import net.argania.core.listeners.InventoryListener;
import net.argania.core.listeners.JoinQuitListener;
import net.argania.core.listeners.LoginListener;
import net.argania.core.listeners.MoveListener;
import net.argania.core.listeners.PacketReceiveListener;
import net.argania.core.listeners.TeleportListener;
import net.argania.core.managers.CombatManager;
import net.argania.core.managers.NameTagManager;
import net.argania.core.managers.guild.AllianceManager;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.store.Store;
import net.argania.core.store.StoreMode;
import net.argania.core.store.modes.StoreMySQL;
import net.argania.core.store.modes.StoreSQLITE;
import net.argania.core.tablist.update.TabLowUpdateTask;
import net.argania.core.tablist.update.TabThread;
import net.argania.core.tasks.CheckValidityTask;
import net.argania.core.tasks.CombatTask;
import net.argania.core.tasks.RespawnCrystalTask;
import net.argania.core.utils.Logger;
import net.argania.core.utils.Ticking;
import net.argania.core.utils.UptakeUtil;
import net.argania.core.utils.enums.Time;

public class GuildPlugin extends JavaPlugin {

    private static GuildPlugin plugin;
    private static Store store = null;

    private boolean enabled = false;

    private static Config revoConfig;
    private static Messages revoMessages;
    private static Commands revoCommands;

    @Override
    public void onEnable() {
        plugin = this;
        new Ticking().start();
        revoConfig = new Config(this);
        revoMessages = new Messages(this);
        revoCommands = new Commands(this);
        TabScheme.reloadTablist();
        if (!Config.ENABLED) {
            Logger.info("This plugin is not activated in the configuration!", "To activate it, set the value 'enabled' to true!", "Plugin will be disabled!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (!registerDatabase()) {
            Logger.info("Can not connect to a MySQL server!", "Plugin will be disabled!");
            store = null;
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        registerManagers();
        registerCommands();
        registerListeners();
        registerTasks();
        start();
        enabled = true;
        checkUpdate();
    }

    @Override
    public void onDisable() {
        stop();
        Bukkit.getScheduler().cancelTasks(this);
        if (enabled) {
            if (store != null && store.isConnected()) {
                store.disconnect();
            }
        }
        enabled = false;
        plugin = null;
    }

    public static GuildPlugin getPlugin() {
        return plugin;
    }

    public static Store getStore() {
        return store;
    }

    public static Config getRevoConfig() {
        return revoConfig;
    }

    public static Messages getRevoMessages() {
        return revoMessages;
    }

    public static Commands getRevoCommands() {
        return revoCommands;
    }

    private void start() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            UserManager.initUser(p);
            NameTagManager.initPlayer(p);
            CombatManager.createPlayer(p);
            UptakeUtil.init(p);
        }
    }

    private void stop() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            CombatManager.removePlayer(p);
            UserManager.leaveFromGame(p);
        }
    }

    private boolean registerDatabase() {
        Logger.info("Register database...");
        switch (StoreMode.getByName(Config.STORE_TYPE)) {
            case MYSQL: {
                store = new StoreMySQL(Config.STORE_MYSQL_HOST, Config.STORE_MYSQL_PORT, Config.STORE_MYSQL_USERNAME, Config.STORE_MYSQL_PASSWORD, Config.STORE_MYSQL_BASE$NAME, Config.STORE_TABLE$PREFIX);
                break;
            }
            case SQLITE: {
                store = new StoreSQLITE(Config.STORE_SQLITE_BASE$NAME, Config.STORE_TABLE$PREFIX);
                break;
            }
            default: {
                Logger.warning("Value of database mode is not valid! Using SQLITE as database!");
                store = new StoreSQLITE(Config.STORE_SQLITE_BASE$NAME, Config.STORE_TABLE$PREFIX);
                break;
            }
        }
        boolean conn = store.connect();
        if (conn) {
            store.update(true, "CREATE TABLE IF NOT EXISTS `{P}guilds` (" + (store.getStoreMode() == MYSQL ? "`id` int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + " `tag` varchar(4) NOT NULL, `name` varchar(32) NOT NULL, `owner` varchar(36) NOT NULL, `leader` varchar(36) NOT NULL, `cuboidWorld` varchar(32) NOT NULL, `cuboidX` int(10) NOT NULL, `cuboidZ` int(10) NOT NULL, `cuboidSize` int(10) NOT NULL, `homeWorld` varchar(32) NOT NULL, `homeX` int(10) NOT NULL, `homeY` int(10) NOT NULL, `homeZ` int(10) NOT NULL, `lives` int(2) NOT NULL DEFAULT '3', `createTime` bigint(13) NOT NULL DEFAULT '0', `expireTime` bigint(13) NOT NULL DEFAULT '0', `lastTakenLifeTime` bigint(13) NOT NULL DEFAULT '0', `pvp` int(1) NOT NULL DEFAULT '0', `banAdmin` varchar(16) NOT NULL, `banTime` bigint(13) NOT NULL, `banReason` varchar(255) NOT NULL);");
            store.update(true, "CREATE TABLE IF NOT EXISTS `{P}members` (" + (store.getStoreMode() == MYSQL ? "`id` int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + " `uuid` varchar(36) NOT NULL, `tag` varchar(4) NOT NULL);");
            store.update(true, "CREATE TABLE IF NOT EXISTS `{P}treasures` (" + (store.getStoreMode() == MYSQL ? "`id` int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + " `tag` varchar(4) NOT NULL, `content` longtext NOT NULL);");
            store.update(true, "CREATE TABLE IF NOT EXISTS `{P}treasure_users` (" + (store.getStoreMode() == MYSQL ? "`id` int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + " `uuid` varchar(36) NOT NULL, `tag` varchar(4) NOT NULL);");
            store.update(true, "CREATE TABLE IF NOT EXISTS `{P}alliances` (" + (store.getStoreMode() == MYSQL ? "`id` int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + " `guild_1` varchar(4) NOT NULL, `guild_2` varchar(4) NOT NULL);");
            store.update(true, "CREATE TABLE IF NOT EXISTS `{P}users` (" + (store.getStoreMode() == MYSQL ? "`id` int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + " `uuid` varchar(36) NOT NULL, `name` varchar(16) NOT NULL, `points` int(10) NOT NULL, `kills` int(10) NOT NULL, `deaths` int(10) NOT NULL);");
        }
        return conn;
    }

    private void registerManagers() {
        Logger.info("Register managers...");
        UserManager.enable();
        GuildManager.enable();
        AllianceManager.enable();
    }

    private void registerCommands() {
        Logger.info("Register commands...");
        if (Commands.GUILD_USER_MAIN_ENABLED) {
            SubCommand.registerCommand(new GuildCommand());
        }
        if (Commands.GUILD_ADMIN_MAIN_ENABLED) {
            SubCommand.registerCommand(new GuildAdminCommand());
        }
        if (Commands.RANKING_ADMIN_MAIN_ENABLED) {
            SubCommand.registerCommand(new RankingAdminCommand());
        }
        if (Commands.RANKING_USER_ENABLED) {
            SubCommand.registerCommand(new RankingCommand());
        }
        if (Commands.TOP_ENABLED) {
            SubCommand.registerCommand(new TopCommand());
        }
        if (Config.ESCAPE_ENABLED && Commands.COMBAT_ENABLED) {
            SubCommand.registerCommand(new CombatCommand());
        }
        SubCommand.registerCommand(new RevoGuildCommand());
    	getCommand("is").setExecutor(new CMDItemShop());
    }

    private void registerListeners() {
        Logger.info("Register listeners...");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ActionsListener(), this);
        pm.registerEvents(new ExplodeListener(), this);
        pm.registerEvents(new MoveListener(), this);
        pm.registerEvents(new TeleportListener(), this);
        pm.registerEvents(new JoinQuitListener(), this);
        pm.registerEvents(new DamageListener(), this);
        pm.registerEvents(new AsyncChatListener(), this);
        pm.registerEvents(new DeathListener(), this);
        pm.registerEvents(new LoginListener(), this);
        if (Config.UPTAKE_ENABLED) {
            pm.registerEvents(new PacketReceiveListener(), this);
        }
        if (Config.ESCAPE_ENABLED && Config.ESCAPE_DISABLED$COMMANDS_ENABLED) {
            pm.registerEvents(new FightCommandsListener(), this);
        }
        if (Config.CUBOID_DISABLED$COMMANDS_ENABLED) {
            pm.registerEvents(new GuildCommandsListener(), this);
        }
        if (Config.ESCAPE_ENABLED) {
            pm.registerEvents(new CombatListener(), this);
        }
        if (Config.TREASURE_ENABLED) {
            pm.registerEvents(new InventoryListener(), this);
        }
    }

    private void registerTasks() {
        Logger.info("Register tasks...");
        new CheckValidityTask().runTaskTimerAsynchronously(this, Time.HOUR.getTick(3), Time.HOUR.getTick(Config.TIME_CHECK));
        new TabLowUpdateTask().runTaskTimerAsynchronously(this, 20L, Time.SECOND.getTick(Config.TABLIST_REFRESH$INTERVAL));
        new RespawnCrystalTask().runTaskTimerAsynchronously(this, 20L, Time.SECOND.getTick(60));
        if (Config.ESCAPE_ENABLED) {
            new CombatTask().runTaskTimerAsynchronously(this, 40L, Time.SECOND.getTick(1));
        }
        new TabThread();
    }

    private void checkUpdate() {
        try {
            String url = "https://raw.githubusercontent.com/userMacieG/RevoGuild/master/version.txt";
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String version = br.readLine();
            String myVersion = GuildPlugin.getPlugin().getDescription().getVersion();
            int build = Integer.parseInt(version.split("-")[1].replace("b", ""));
            int myBuild = Integer.parseInt(myVersion.split("-")[1].replace("b", ""));
            if (!myVersion.equalsIgnoreCase(version) && build > myBuild) {
                Logger.info("-------------[ ArganiaCore ]-------------");
                Logger.info(" > Znaleziono nowa wersje pluginu!", "");
                Logger.info(" > Zainstalowana wersja: " + myVersion);
                Logger.info(" > Aktualna wersja: " + version, "");
                Logger.info(" > Pobierz najnowsza wersje z: https://www.piechuucode.pl/release/arganiacore // wkrotce");
                Logger.info("---------------------------------------");
            }
            conn.disconnect();
        } catch (Exception e) {
            Logger.warning("Blad podczas sprawdzania aktualizacji!");
        }
    }

}