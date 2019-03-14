package net.argania.core.tablist.update;

import org.bukkit.scheduler.BukkitRunnable;

import net.argania.core.managers.TabManager;

public class TabLowUpdateTask extends BukkitRunnable {

    @Override
    public void run() {
        TabManager.updateSlots();
    }

}
