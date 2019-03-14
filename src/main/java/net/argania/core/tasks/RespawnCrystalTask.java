package net.argania.core.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import net.argania.core.managers.guild.GuildManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.utils.UptakeUtil;

public class RespawnCrystalTask extends BukkitRunnable {

    @Override
    public void run() {
        for (Guild g : GuildManager.getGuilds().values()) {
            UptakeUtil.respawnGuild(g);
        }
    }

}
