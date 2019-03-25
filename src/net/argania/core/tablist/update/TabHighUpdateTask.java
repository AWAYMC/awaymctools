package net.argania.core.tablist.update;

import net.argania.core.managers.TabManager;
import org.bukkit.scheduler.BukkitRunnable;

class TabHighUpdateTask extends BukkitRunnable {

    @Override
    public void run() {
        TabManager.updateAll();
    }

}
