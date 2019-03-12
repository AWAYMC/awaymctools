package net.argania.core.objects.guild;

import net.argania.core.GuildPlugin;
import net.argania.core.Utils.ItemSerializer;
import net.argania.core.Utils.Util;
import net.argania.core.data.Config;
import net.argania.core.store.modes.store.Entry;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Treasure implements Entry {

    private Guild owner;
    private Inventory inv = Bukkit.createInventory(null, 9 * Config.TREASURE_ROWS, Util.fixColor(Config.TREASURE_TITLE));
    private ItemStack[] items = new ItemStack[9 * Config.TREASURE_ROWS];

    public Treasure(Guild g) {
        this.owner = g;
        insert();
    }

    public Treasure(Guild g, ResultSet rs) throws SQLException {
        rs.next();
        this.owner = g;
        ItemStack[] items = ItemSerializer.stringToItems(rs.getString("content"));
        if (items.length > this.items.length) {
            this.items = Arrays.copyOfRange(items, 0, this.items.length);
        } else {
            this.items = items;
        }
    }

    private String getContent() {
        return ItemSerializer.itemsToString(this.items);
    }

    @Override
    public void insert() {
        GuildPlugin.getStore().update(true, "INSERT INTO `{P}treasures` (`id`,`tag`,`content`) VALUES(NULL, '" + this.owner.getTag() + "', '" + getContent() + "')");
    }

    @Override
    public void update(boolean now) {
        String query = "UPDATE `{P}treasures` SET `content` ='" + getContent() + "' WHERE `tag` = '" + this.owner.getTag() + "'";
        GuildPlugin.getStore().update(now, query);
    }

    @Override
    public void delete() {
        GuildPlugin.getStore().update(true, "DELETE FROM `{P}treasures` WHERE `tag` = '" + this.owner.getTag() + "'");
    }

    public Guild getOwner() {
        return owner;
    }

    public void setOwner(Guild owner) {
        this.owner = owner;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public void setItems(ItemStack[] items) {
        this.items = items;
    }

}
