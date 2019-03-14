package net.argania.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.argania.core.data.Messages;
import net.argania.core.managers.guild.AllianceManager;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.utils.ParticleUtil;
import net.argania.core.utils.ParticleUtil.ParticleType;
import net.argania.core.utils.Util;

public class DamageListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getDamage() < 0) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (Util.getDamager(e) == null) {
            return;
        }
        Player p = (Player) e.getEntity();
        Player d = Util.getDamager(e);
        if (p == d) {
            return;
        }
        Guild pg = GuildManager.getGuild(p);
        Guild dg = GuildManager.getGuild(d);
        if (dg == null || pg == null) {
            return;
        }
        if (dg == pg) {
            if (dg.isPvp()) {
                e.setDamage(0);
            } else {
                e.setCancelled(true);
                ParticleUtil.sendPartileToPlayer(d, ParticleType.HEART, p.getEyeLocation(), 0.25F, 0.25F, 0.25F, 8, 3);
                Util.sendMessage(d, Messages.ERROR_CANT_ATTACK$PLAYER);
            }
        } else if (AllianceManager.hasAlliance(pg, dg)) {
            e.setDamage(0);
            Util.sendMessage(d, Messages.ERROR_CANT_ATTACK$PLAYER);
        }
    }

}
