package net.argania.core.listeners;

import net.argania.core.GuildPlugin;
import net.argania.core.objects.guild.Guild;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.AllianceManager;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.packetlistener.events.PacketReceiveEvent;
import net.argania.core.utils.*;
import net.argania.core.utils.ParticleUtil.ParticleType;
import net.argania.core.utils.Reflection.FieldAccessor;
import net.argania.core.utils.enums.Time;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class PacketReceiveListener implements Listener {

    private static final Class<?> useEntityClass = Reflection.getMinecraftClass("PacketPlayInUseEntity");
    private static final FieldAccessor<Integer> useEntityA = Reflection.getField(useEntityClass, "a", int.class);

    @EventHandler
    public void onPacketReceive(PacketReceiveEvent e) {
        Object packet = e.getPacket();
        if (!packet.getClass().getSimpleName().equals("PacketPlayInUseEntity")) {
            return;
        }
        int id = useEntityA.get(packet);
        Player p = e.getPlayer();
        final Guild g = GuildManager.getGuild(p.getLocation());
        if (g == null) {
            return;
        }
        if (g.isMember(UserManager.getUser(p).getUuid())) {
            return;
        }
        int myId = UptakeUtil.getId(p, g);
        if (myId < 0) {
            return;
        }
        if (id != myId) {
            return;
        }
        Guild o = GuildManager.getGuild(p);
        if (o ==null) {
            return;
        }
        if (AllianceManager.hasAlliance(g, o)) {
            return;
        }
        if ((g.getLastTakenLifeTime() + Time.HOUR.getTime(Config.UPTAKE_LIVES_TIME)) > System.currentTimeMillis()) {
            Util.sendMessage(p, Messages.ERROR_CANT_TAKE$LIFE);
            return;
        }
        if (o.getLives() < Config.UPTAKE_LIVES_MAX) {
            o.addLives(1);
        }
        Location l = g.getCuboid().getCenter();
        l.setY(62);
        if (g.getLives() <= 1) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    GuildManager.removeGuild(g);
                }
            }.runTask(GuildPlugin.getPlugin());
            Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_TAKEN, g, o, p));
            p.playSound(g.getCuboid().getCenter(), Sound.ENDERDRAGON_DEATH, 20, 20);
            for (int i = 0; i < 10; i++) {
                g.getCuboid().getWorld().strikeLightning(g.getCuboid().getCenter());
            }
            ParticleUtil.sendParticleToLocation(l, ParticleType.ENCHANTMENT_TABLE, 2, 2, 2, 9, 10);
        } else {
            g.removeLives(1);
            g.setLastTakenLifeTime(System.currentTimeMillis());
            g.update(false);
            p.playSound(g.getCuboid().getCenter(), Sound.ANVIL_USE, 20, 20);
            ParticleUtil.sendParticleToLocation(l, ParticleType.FLAME, 0.5F, 0.5F, 0.5F, 9, 10);
            Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_LIFE$TAKEN, g, o, p));
        }

    }

}
