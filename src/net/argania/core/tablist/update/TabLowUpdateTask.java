package net.argania.core.tablist.update;

import net.argania.core.managers.TabManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map.Entry;

public class TabLowUpdateTask extends BukkitRunnable {

    @Override
    public void run() {
        TabManager.updateSlots();
    }

}
