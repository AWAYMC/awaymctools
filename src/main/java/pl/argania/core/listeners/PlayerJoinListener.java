package pl.argania.core.listeners;

import com.connorlinfoot.titleapi.TitleAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.argania.core.Utils.ChatColorUtils;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public  void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        TitleAPI.sendTitle(p,20,50,20, ChatColorUtils.colored("&f&lAR&c&lGANIA"), ChatColorUtils.colored("&7Dziekujemy ze wybrales nas :)"));

    }
}
