package net.argania.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.objects.guild.Guild;

public class LoginListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerLoginEvent e) {
        Guild g = GuildManager.getGuild(e.getPlayer());
        if (g == null)
            return;
        if (!g.isBanned())
            return;

        String kickMsg = Messages.parse(Messages.BAN$REASON, g);

        e.disallow(Result.KICK_BANNED, kickMsg);

    }

}
