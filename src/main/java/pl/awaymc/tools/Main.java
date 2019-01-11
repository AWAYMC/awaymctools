package pl.awaymc.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main inst;
    public static String prefix = "";

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