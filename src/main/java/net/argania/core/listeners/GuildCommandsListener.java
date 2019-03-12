package net.argania.core.listeners;

import net.argania.core.Utils.Util;
import net.argania.core.data.Config;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.users.UserManager;
import net.argania.core.objects.guild.Guild;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class GuildCommandsListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        Guild g = GuildManager.getGuild(p.getLocation());


        if (!Config.CUBOID_DISABLED$COMMANDS_ENABLED) return;
        if (g == null) return;
        if (g.isMember(UserManager.getUser(p).getUuid())) return;
        for (String s : Config.CUBOID_DISABLED$COMMANDS_COMMANDS) {
            if (!msg.contains("/" + s))
                continue;
            e.setCancelled(true);
            if (Config.CUBOID_DISABLED$COMMANDS_NOTIFY_ENABLED)
                Util.sendMessage(p, Util.fixColor(Config.CUBOID_DISABLED$COMMANDS_NOTIFY_MESSAGE));
        }

    }

}
