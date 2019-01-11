package pl.awaymc.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pl.awaymc.tools.utils.IS;

public class CommandKit implements CommandExecutor, Listener {

    private Inventory kity = Bukkit.createInventory(null, 18, "§aZestawy");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("kit")) {
            if(!(sender instanceof Player)) {

            }
            else {
                Player p = (Player)sender;

                kity.setItem(3, IS.createItemStack(Material.STONE_PICKAXE, 1, "§aKit Gracz", IS.createLore("§bLPM aby odebrac zestaw", "§bPPM aby zobaczyc zawartosc zestawu")));
                kity.setItem(4, IS.createItemStack(Material.IRON_SWORD, 1, "§aKit VIP", IS.createLore("§bLPM aby odebrac zestaw", "§bPPM aby zobaczyc zawartosc zestawu")));
                kity.setItem(5, IS.createItemStack(Material.DIAMOND_SWORD, 1, "§aKit SVIP", IS.createLore("§bLPM aby odebrac zestaw", "§bPPM aby zobaczyc zawartosc zestawu")));
                kity.setItem(12, IS.createItemStack(Material.COOKED_BEEF, 1, "§aKit Jedzenie", IS.createLore("§bLPM aby odebrac zestaw", "§bPPM aby zobaczyc zawartosc zestawu")));
                kity.setItem(14, IS.createItemStack(Material.ENDER_CHEST, 1, "§aKit Enderchest", IS.createLore("§bLPM aby odebrac zestaw", "§bPPM aby zobaczyc zawartosc zestawu")));

                p.openInventory(kity);
            }
        }
        return false;
    }

    @EventHandler
    public void click(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
    }

}
