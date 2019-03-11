package pl.argania.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.argania.core.commands.CommandItemShop;
import pl.argania.core.listeners.PlayerJoinListener;

public class Main extends JavaPlugin {

    public void onEnable(){
        getLogger().info("[ARGANIA_CORE] uruchamianie plikow serwerowych");
        regListeners();
        getCommand("is").setExecutor(new CommandItemShop());
    }
    public static void regListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        regListeners(Main.getPlugin(), "pl.argania.core.listeners");
        pm.registerEvents(new PlayerJoinListener(), this);

    }
}
