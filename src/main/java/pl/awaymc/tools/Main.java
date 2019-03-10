package pl.awaymc.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.awaymc.tools.commands.*;
import pl.awaymc.tools.listeners.InventoryClickListener;

public final class Main extends JavaPlugin {

    private static Main inst;
    public static String prefix = "§8[§9§oAWAYMC§8]";

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[AWAYMC] Uruchamianie pluginu AWAYTOOLS");

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[AWAYMC] Wylaczanie pluginu AWAYTOOLS");
    }

    private void registerCommands() {
        RC("gamemode", new CommandGamemode());
        RC("broadcast", new CommandBroadcast());
        RC("heal", new CommandHeal());
        RC("feed", new CommandFeed());
        RC("sklep", new CommandSklep());
        RC("bct", new CommandBroadcastTitle());
<<<<<<< HEAD
        RC("is", new CommandItemShop());
=======
        RC("coins", new CommandCoins());
>>>>>>> 6674629c5e5596fe22e67b44f4ab2c29490fc90d
    }
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
    }

    /*
        INITIAL
     */

    public static Main getInst() {
        return inst;
    }
    private static void RC(String commandName, CommandExecutor executor) {
        getInst().getCommand(commandName).setExecutor(executor);
    }
    private static void RL(Listener listener) {
        getInst().getServer().getPluginManager().registerEvents(listener, getInst());
    }

}