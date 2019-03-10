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
        getCommand("gamemode").setExecutor(new CommandGamemode());
        getCommand("broadcast").setExecutor(new CommandBroadcast());
        getCommand("heal").setExecutor(new CommandHeal());
        getCommand("feed").setExecutor(new CommandHeal());
        getCommand("sklep").setExecutor(new CommandSklep());
        getCommand("bct").setExecutor(new CommandBroadcastTitle());
        getCommand("is").setExecutor(new CommandItemShop());
        getCommand("coins").setExecutor(new CommandCoins());
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