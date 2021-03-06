package net.argania.core.tablist;

import net.argania.core.GuildPlugin;
import net.argania.core.tablist.packets.PacketManager;
import net.argania.core.tablist.packets.PlayerInfoAction;
import net.argania.core.utils.Util;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TabData {

    public static final int ROWS = 20;
    public static final int COLUMNS = 4;
    private static final Profile[][] PROFILES = new Profile[ROWS][COLUMNS];

    private PacketManager packetManager = new PacketManager();

    static {
        int base = 97;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                char first = (char) (base + col);
                char second = (char) (base + row);
                String name = "!!UPDATEMC" + first + "" + second;
                PROFILES[row][col] = new Profile(UUID.randomUUID(), name);
            }
        }
    }

    private final String[][] slots = new String[ROWS][COLUMNS];
    private boolean locked = false;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TabData() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS; row++) {
                this.slots[row][col] = "";
            }
        }
    }

    public void sendTab() {
        locked = true;
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS; row++) {
                Profile profile = PROFILES[row][col];
                String slot = this.slots[row][col];
                packetManager.sendPlayerListPacket(getPlayer(), profile, slot, PlayerInfoAction.ADD_PLAYER);
            }
        }
    }

    public void setSlot(int row, int column, String text) {
        if (locked) {
            throw new IllegalArgumentException("Can not set slot after tab send!");
        }
        this.slots[row][column] = Util.fixColor(text);
    }

    public void updateSlot(int row, int column, String text) {
        this.slots[row][column] = Util.fixColor(text);
        packetManager.sendPlayerListPacket(getPlayer(), PROFILES[row][column], slots[row][column], PlayerInfoAction.UPDATE_DISPLAY_NAME);
    }

    public void setHeaderAndFooter(String header, String footer) {
        packetManager.sendTablistHeaderPacket(getPlayer(), header, footer);
    }

}
