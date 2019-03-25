package net.argania.core.commands;

import org.bukkit.entity.Player;

import net.argania.core.GuildPlugin;
import net.argania.core.data.Commands;
import net.argania.core.utils.Util;

public class RevoGuildCommand extends SubCommand {

    public RevoGuildCommand() {
        super("revoguild", "informacje o pluginie", "/revoguild", Commands.GUILD_USER_MAIN_PERMISSION);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Util.sendMessage(p, " &7&m--------&r &cArganiaCore &7&m--------&r");
        Util.sendMessage(p, " &8» &7Wersja: &c" + GuildPlugin.getPlugin().getDescription().getVersion());
        return true;
    }
}
