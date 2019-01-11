package pl.awaymc.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import pl.awaymc.tools.commands.CommandBroadcast;
import pl.awaymc.tools.commands.CommandGamemode;

public final class Main extends JavaPlugin {

    private static Main inst;
    public static String prefix = "§8[§9§oAWAYMC§8]";

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[AWAYMC] Uruchamianie pluginu AWAYTOOLS");

        registerCommands();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[AWAYMC] Wylaczanie pluginu AWAYTOOLS");
    }

    private void registerCommands() {
        RC("gamemode", new CommandGamemode());
        RC("broadcast", new CommandBroadcast());
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


}