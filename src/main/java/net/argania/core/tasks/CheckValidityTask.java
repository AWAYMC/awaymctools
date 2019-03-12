package net.argania.core.tasks;

import net.argania.core.Utils.Util;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.objects.guild.Guild;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class CheckValidityTask extends BukkitRunnable {

    @Override
    public void run() {
        Collection<Guild> c = GuildManager.getGuilds().values();
        for (Guild g : c) {
            if (g.getExpireTime() < System.currentTimeMillis()) {
                GuildManager.removeGuild(g);
                Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_EXPIRED, g).replace("{X}", Integer.toString(g.getCuboid().getCenterX())).replace("{Z}", Integer.toString(g.getCuboid().getCenterZ())));
            }
        }
    }

}
