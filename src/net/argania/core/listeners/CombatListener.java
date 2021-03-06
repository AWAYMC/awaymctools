package net.argania.core.listeners;

import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.CombatManager;
import net.argania.core.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
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
        if (Config.ESCAPE_ENABLED) {
            if (CombatManager.isInFight(p)) {
                Util.sendMessage(p, Messages.INFO_FIGHT_START);
            }
            if (CombatManager.isInFight(d)) {
                Util.sendMessage(d, Messages.INFO_FIGHT_START);
            }
            CombatManager.setLastFight(p);
            CombatManager.setLastFight(d);
        }

    }

}
