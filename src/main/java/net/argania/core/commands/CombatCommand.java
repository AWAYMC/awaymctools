package net.argania.core.commands;

import net.argania.core.Utils.Util;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.CombatManager;
import org.bukkit.entity.Player;

public class CombatCommand extends SubCommand {

    public CombatCommand() {
        super(Commands.COMBAT_NAME, Commands.COMBAT_DESCRIPTION, Commands.COMBAT_USAGE, Commands.COMBAT_PERMISSION, Commands.COMBAT_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        return Util.sendMessage(p, Messages.COMBAT$INFO.replace("{TIME}", CombatManager.getTime(p)));
    }
}