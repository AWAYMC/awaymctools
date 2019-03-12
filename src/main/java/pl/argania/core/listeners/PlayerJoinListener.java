package pl.argania.core.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import pl.argania.core.Utils.ChatColorUtils;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public  void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        p.sendTitle(ChatColorUtils.colored("&cARGANIA.PL"), ChatColorUtils.colored("&fDziekujemy ze nas odwiedziles! &4<3"));
        p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_PICKAXE, 1) });
        p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.COOKED_BEEF, 32) });
        p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ENDER_CHEST, 1) });
    }
}
