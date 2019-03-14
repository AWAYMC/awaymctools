package net.argania.core.commands;


import org.bukkit.entity.Player;

import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.CombatManager;
import net.argania.core.utils.Util;

public class CombatCommand extends SubCommand {

    public CombatCommand() {
        super(Commands.COMBAT_NAME, Commands.COMBAT_DESCRIPTION, Commands.COMBAT_USAGE, Commands.COMBAT_PERMISSION, Commands.COMBAT_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        return Util.sendMessage(p, Messages.COMBAT$INFO.replace("{TIME}", CombatManager.getTime(p)));
    }
}