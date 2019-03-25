package net.argania.core.listeners;

import net.argania.core.objects.guild.Guild;
import net.argania.core.data.Config;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();

        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return;


        Inventory inv = e.getInventory();
        if (!Util.fixColor(Config.TREASURE_TITLE).equalsIgnoreCase(inv.getName()))
            return;
        g.closeTreasure(p, inv);
    }

}
