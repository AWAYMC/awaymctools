package pl.awaymc.tools.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.awaymc.tools.utils.ChatUtil;
import pl.awaymc.tools.utils.ItemBuilder;

public class SkleGUI {

    public static void sklep(Player p){
        Inventory inv = Bukkit.createInventory(p ,9, (ChatUtil.fixColor("&cSKLEP")));
        ItemBuilder miecz1 = (ItemBuilder) new ItemBuilder(Material.DIAMOND_SWORD).getTitle((ChatUtil.fixColor("&c&LMIECZ 5/2"))).getLore(ChatUtil.fixColor("&8>> &7Cena kupna: &c20 COINSOW!")).getLore(ChatUtil.fixColor("&8>> &a&lNACISNIJ ABY ZAKUPIC PRZEDMIOT!")).addEnchantment(Enchantment.DAMAGE_ALL, 5).addEnchantment(Enchantment.FIRE_ASPECT, 2);
        inv.setItem(0 , miecz1.build());
    }
}
