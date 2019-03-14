package net.argania.core.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.enums.NotifyType;
import net.argania.core.managers.guild.AllianceManager;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.utils.Util;
import net.argania.core.utils.enums.Time;

public class MoveListener implements Listener {

    private static final HashMap<Guild, Long> times = new HashMap<>();

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent e) {
        if (!Config.MOVEMENT_NOTIFY_ENABLED) {
            return;
        }
        Player p = e.getPlayer();
        int xfrom = e.getFrom().getBlockX();
        int zfrom = e.getFrom().getBlockZ();
        int yfrom = e.getFrom().getBlockY();
        int xto = e.getTo().getBlockX();
        int yto = e.getTo().getBlockY();
        int zto = e.getTo().getBlockZ();
        if ((xfrom != xto) || (zfrom != zto) || (yfrom != yto)) {
            Guild from = GuildManager.getGuild(e.getFrom());
            Guild to = GuildManager.getGuild(e.getTo());
            if ((from == null) && (to != null)) {
                if (to.getMembers().contains(p.getUniqueId())) {
                    Util.sendMessage(p, Messages.parse(NotifyType.GUILD.getMessageIn(), to));
                } else if (AllianceManager.hasAlliance(to, GuildManager.getGuild(p))) {
                    Util.sendMessage(p, Messages.parse(NotifyType.GUILD_ALLIANCE.getMessageIn(), to));
                } else {
                    Util.sendMessage(p, Messages.parse(NotifyType.GUILD_ENEMY.getMessageIn(), to));
                }
                if (Config.MOVEMENT_NOTIFY_INTRUDER_ENABLED) {
                    if (to.isMember(p.getUniqueId())) {
                        return;
                    }
                    if (p.isOp() || p.hasPermission("revoguild.notify.bypass")) {
                        return;
                    }
                    Long time = times.get(to);
                    if (time == null || System.currentTimeMillis() - time >= Time.SECOND.getTime(30)) {
                        Util.sendMessage(to.getOnlineMembers(), Messages.INFO_MOVE_INTRUDER);
                        times.put(to, System.currentTimeMillis());
                    }
                }
            } else if ((from != null) && (to == null)) {
                if (from.getMembers().contains(p.getUniqueId())) {
                    Util.sendMessage(p, Messages.parse(NotifyType.GUILD.getMessageOut(), from));
                } else if (AllianceManager.hasAlliance(from, GuildManager.getGuild(p))) {
                    Util.sendMessage(p, Messages.parse(NotifyType.GUILD_ALLIANCE.getMessageOut(), from));
                } else {
                    Util.sendMessage(p, Messages.parse(NotifyType.GUILD_ENEMY.getMessageOut(), from));
                }
            }
        }
    }

}
