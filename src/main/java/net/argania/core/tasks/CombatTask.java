package net.argania.core.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.argania.core.data.Messages;
import net.argania.core.managers.CombatManager;
import net.argania.core.utils.Util;

public class CombatTask extends BukkitRunnable {

    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != null) {
                if (CombatManager.wasInFight(p) && CombatManager.isInFight(p)) {
                    Util.sendMessage(p, Messages.INFO_FIGHT_END);
                }
            }
        }
    }

}
