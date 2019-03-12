package pl.argania.core;

import org.bukkit.plugin.java.JavaPlugin;
import pl.argania.core.commands.CommandItemShop;
import pl.argania.core.listeners.PlayerJoinListener;

public class Main extends JavaPlugin {

    public void onEnable(){
        getLogger().info("[ARGANIA_CORE] uruchamianie plikow serwerowych");
        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    private void registerCommands() {
        getCommand("is").setExecutor(new CommandItemShop());
    }
}
