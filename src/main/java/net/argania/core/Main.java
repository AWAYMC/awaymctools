package net.argania.core;

import net.argania.core.commands.*;
import net.argania.core.listeners.PlayerChatListener;
import net.argania.core.listeners.PlayerJoinListener;
import net.argania.core.listeners.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Main extends JavaPlugin {

    public static BukkitTask task;
    public static List<String> msg;
    int i;


    private static Main inst;

    public static Main getInst() {
        return Main.inst;
    }

    public void onEnable() {
        inst = this;
        registerEvents();
        registerCommands();
        getLogger().info("---------( ARGANIACORE )--------");
        getLogger().info(">> Wlaczanie core...");
        getLogger().info(">> Licencja Zaakceptowana baw sie dobrze");
        getLogger().info(">> Wersja: 1.0");
        getLogger().info(">> Czekaj na update");
        getLogger().info(">> Autorzy: Piechuuu, Adrianekkk");
        getLogger().info(">> Projekt stworzony z mysla o hardcore");
        getLogger().info("---------( ARGANIACORE )--------");
    }

    private void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("itemshop").setExecutor(new ItemshopCommand());
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("chat").setExecutor(new ChatCommand());
        getCommand("op").setExecutor(new OpCommand());
        getCommand("op").setExecutor(new DeopCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());



    }

    public static Main getPlugin() {
        if (Main.inst == null) {
            return new Main();
        }
        return Main.inst;
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
    }

    public void onDisable() {
        getLogger().info("---------( ARGANIACORE )--------");
        getLogger().info(">> Wylaczanie core...");
        getLogger().info(">> Wersja: 1.0");
        getLogger().info(">> Czekaj na update");
        getLogger().info(">> Autorzy: Piechuuu, Adrianekkk");
        getLogger().info(">> Projekt stworzony z mysla o hardcore");
        getLogger().info("---------( ARGANIACORE )--------");
    }
}
