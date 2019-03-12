package net.argania.core.listeners;

import net.argania.core.Utils.UptakeUtil;
import net.argania.core.Utils.Util;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.CombatManager;
import net.argania.core.managers.NameTagManager;
import net.argania.core.managers.TabManager;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.users.UserManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.objects.users.User;
import net.argania.core.packetlistener.PacketManager;
import net.argania.core.tablist.update.TabThread;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        PacketManager.registerPlayer(p);
        NameTagManager.initPlayer(p);
        UserManager.joinToGame(p);
        UserManager.initUser(p);
        if (Config.TABLIST_ENABLED) {
            TabManager.createTab(p);
        }
        if (Config.ESCAPE_ENABLED) {
            CombatManager.createPlayer(p);
        }
        UptakeUtil.init(p);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        escape(e.getPlayer());
        UserManager.leaveFromGame(e.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        escape(e.getPlayer());
        UserManager.leaveFromGame(e.getPlayer());
    }

    private void escape(Player p) {
        if (!Config.ESCAPE_ENABLED) {
            return;
        }
        if (CombatManager.isInFight(p)) {
            return;
        }
        User u = UserManager.getUser(p);
        Guild pGuild = GuildManager.getGuild(p);
        String pGuildTag = pGuild != null ? Messages.parse(Config.RANKING_DEATH_TAG, pGuild) : "";
        String mes = Config.ESCAPE_BROADCAST;
        mes = mes.replace("{PGUILD}", pGuildTag);
        mes = mes.replace("{PLAYER}", p.getName());
        u.addDeaths(1);
        u.removePoints(Config.ESCAPE_LOSE$POINTS);
        p.setHealth(0D);
        TabThread.restart();
        Util.sendMessage(Bukkit.getOnlinePlayers(), mes);
    }

}
