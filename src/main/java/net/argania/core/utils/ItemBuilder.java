package net.argania.core.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemBuilder {

    private Material mat;
    private int amount;
    private short data;
    private String title;
    private List<String> lore;
    private HashMap<Enchantment, Integer> enchants;

    public ItemBuilder(Material mat) {
        this(mat, 1);
    }

    public ItemBuilder(Material mat, int amount) {
        this(mat, amount, (short)0);
    }

    public ItemBuilder(Material mat, short data) {
        this(mat, 1, data);
    }

    public ItemBuilder(Material mat, int amount, short data) {
        this.title = null;
        this.lore = new ArrayList<>();
        this.enchants = new HashMap<>();
        this.mat = mat;
        this.amount = amount;
        this.data = data;
    }

    public ItemBuilder getTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemBuilder getLore(String lore) {
        this.lore.add(lore);
        return this;
    }

    public ItemBuilder getLore(List<String> lore) {
        this.lore.addAll(lore);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchant, int level) {
        enchants.remove(enchant);
        enchants.put(enchant, level);
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(mat, amount, data);
        ItemMeta meta = item.getItemMeta();
        if (title != null) {
            meta.setDisplayName(title);
        }
        if (!this.lore.isEmpty()) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        item.addUnsafeEnchantments(enchants);
        return item;
    }
}

