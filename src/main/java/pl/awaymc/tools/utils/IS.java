package pl.awaymc.tools.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class IS {

    public static ItemStack createItemStack(Material m) {
        return new ItemStack(m);
    }
    public static ItemStack createItemStack(Material m, int amount) {
        return new ItemStack(m, amount);
    }
    public static ItemStack createItemStack(Material m, int amount, String name) {
        ItemStack is = new ItemStack(m, amount); {
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(name);
            is.setItemMeta(im);
        }
        return is;
    }
    public static ItemStack createItemStack(Material m, int amount, String name, List<String> lore) {
        ItemStack is = new ItemStack(m, amount); {
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(name);
            im.setLore(lore);
            is.setItemMeta(im);
        }
        return is;
    }
    public static ItemStack createItemStack(Material m, int amount, short data) {
        return new ItemStack(m, amount, data);
    }
    public static ItemStack createItemStack(Material m, int amount, short data, String name) {
        ItemStack is = new ItemStack(m, amount, data); {
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(name);
            is.setItemMeta(im);
        }
        return is;
    }
    public static ItemStack createItemStack(Material m, int amount, short data, String name, List<String> lore) {
        ItemStack is = new ItemStack(m, amount, data); {
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(name);
            im.setLore(lore);
            is.setItemMeta(im);
        }
        return is;
    }

    public static List<String> createLore(String lin1) {
        List<String> lore = new ArrayList<String>();
        lore.add(lin1);
        return lore;
    }
    public static List<String> createLore(String lin1, String lin2) {
        List<String> lore = new ArrayList<String>();
        lore.add(lin1);
        lore.add(lin2);
        return lore;
    }
    public static List<String> createLore(String lin1, String lin2, String lin3) {
        List<String> lore = new ArrayList<String>();
        lore.add(lin1);
        lore.add(lin2);
        lore.add(lin3);
        return lore;
    }
    public static List<String> createLore(String lin1, String lin2, String lin3, String lin4) {
        List<String> lore = new ArrayList<String>();
        lore.add(lin1);
        lore.add(lin2);
        lore.add(lin3);
        lore.add(lin4);
        return lore;
    }
    public static List<String> createLore(String lin1, String lin2, String lin3, String lin4, String lin5) {
        List<String> lore = new ArrayList<String>();
        lore.add(lin1);
        lore.add(lin2);
        lore.add(lin3);
        lore.add(lin4);
        lore.add(lin5);
        return lore;
    }

}
