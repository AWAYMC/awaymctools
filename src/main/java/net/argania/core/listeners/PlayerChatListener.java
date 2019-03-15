package net.argania.core.listeners;

import net.argania.core.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final PermissionUser pe = PermissionsEx.getUser(p);

        if (pe.inGroup("ROOT")) {
            e.setFormat(ChatUtil.fixColor("&8[&4ROOT&8] &7" + e.getPlayer().getName() + " &8>> &c" + e.getMessage()));
        }
        else if (pe.inGroup("HEADADMIN")) {
            e.setFormat(ChatUtil.fixColor("&8[&4HA&8] &7" + e.getPlayer().getName() + " &8>> &c" + e.getMessage()));
        }
        else if (pe.inGroup("ADMIN")) {
            e.setFormat(ChatUtil.fixColor("&8[&cA&8] &7" + e.getPlayer().getName() + " &8>> &c" + e.getMessage()));
        }
        else if (pe.inGroup("MODERATOR")) {
            e.setFormat(ChatUtil.fixColor("&8[&2M&8] &7" + e.getPlayer().getName() + " &8>> &a" + e.getMessage()));
        }
        else if (pe.inGroup("HELPER")) {
            e.setFormat(ChatUtil.fixColor("&8[&eH&8] &7" + e.getPlayer().getName() + " &8>> &6" + e.getMessage()));
        }
        else if (pe.inGroup("YT")) {
            e.setFormat(ChatUtil.fixColor("&8[&fY&4T&8] &7" + e.getPlayer().getName() + " &8>> &f" + e.getMessage()));
        }
        else if (pe.inGroup("VIP")) {
            e.setFormat(ChatUtil.fixColor("&8[&6VIP&8] &7" + e.getPlayer().getName() + " &8>> &e" + e.getMessage()));
        }
        else if (pe.inGroup("GRACZ")) {
            e.setFormat(ChatUtil.fixColor("&7" + e.getPlayer().getName() + " &8>> &7" + e.getMessage()));
        }
    }

}

