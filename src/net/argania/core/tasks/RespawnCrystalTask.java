package net.argania.core.tasks;

import net.argania.core.objects.guild.Guild;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.utils.UptakeUtil;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnCrystalTask extends BukkitRunnable {

    @Override
    public void run() {
        for (Guild g : GuildManager.getGuilds().values()) {
            UptakeUtil.respawnGuild(g);
        }
    }

}
