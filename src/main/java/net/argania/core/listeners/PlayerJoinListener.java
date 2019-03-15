package net.argania.core.listeners;

import net.argania.core.utils.ChatUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onpJoin(final PlayerJoinEvent e) {

        Player p = e.getPlayer();
        e.setJoinMessage(null);
        if (p.hasPlayedBefore()) {
            p.sendTitle(ChatUtil.fixColor("&cARGANIA.PL"), ChatUtil.fixColor("&fWitamy na serwerze &cHARDCORE&f!"));
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_PICKAXE, 1) });
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.COOKED_BEEF, 32) });
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ENDER_CHEST, 1) });
            for (int i = 0; i < 128; ++i) {
                p.sendMessage(" ");
            }
        }
    }
}

