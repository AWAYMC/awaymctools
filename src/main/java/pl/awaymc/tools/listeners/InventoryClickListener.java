package pl.awaymc.tools.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.awaymc.tools.utils.ChatUtil;

import java.util.ArrayList;
import java.util.List;

public class InventoryClickListener implements Listener {

    public ItemStack setNameAndLore(final Material material, final int amount, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        final ArrayList<String> lorez = new ArrayList<String>();
        for (final String mylores : lore) {
            lorez.add(ChatColor.translateAlternateColorCodes('&', mylores));
        }
        ((ItemMeta) meta).setLore((List)lorez);
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final Player p = (Player)event.getWhoClicked();
        final Inventory inv = (Inventory)p.getInventory();
        if ((ChatUtil.fixColor("&CSKLEP")).equalsIgnoreCase(event.getClickedInventory().getName())) {
            event.setCancelled(true);
            if (event.getSlot() == 10) {
                if (inv.containsAtLeast(this.setNameAndLore(Material.DOUBLE_PLANT, 1, "&3COINS", new String[0]), 5)) {
                    inv.removeItem(new ItemStack[]{this.setNameAndLore(Material.DOUBLE_PLANT, 5, "&3COINS", new String[0])});
                    inv.addItem(new ItemStack[]{new ItemStack(Material.DIAMOND_SWORD, 8, (short) 1)});
                    p.sendMessage(ChatUtil.fixColor("&8>> &7Przedmiot zostal zakupiony!"));
                } else {
                    p.sendMessage("&4Blad: &cNie masz wystarczajaco COINSOW!");
                }
            }
        }
    }
}
