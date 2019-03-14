package net.argania.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.argania.core.data.Config;
import net.argania.core.managers.CombatManager;
import net.argania.core.utils.Util;

public class FightCommandsListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        if (CombatManager.isInFight(p))
            return;

        if (!Config.ESCAPE_DISABLED$COMMANDS_ENABLED)
            return;

        for (String s : Config.ESCAPE_DISABLED$COMMANDS_COMMANDS) {
            if (!msg.contains("/" + s))
                continue;
            e.setCancelled(true);
            if (Config.ESCAPE_DISABLED$COMMANDS_NOTIFY_ENABLED)
                Util.sendMessage(p, Util.fixColor(Config.ESCAPE_DISABLED$COMMANDS_NOTIFY_MESSAGE));
            return;

        }
    }

}
