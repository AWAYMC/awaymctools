package net.argania.core.commands.guild.admin;

import net.argania.core.Utils.Util;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.objects.guild.Guild;
import org.bukkit.entity.Player;

public class TeleportCommand extends SubCommand {

    public TeleportCommand() {
        super(Commands.GUILD_ADMIN_TELEPORT_NAME, Commands.GUILD_ADMIN_TELEPORT_DESCRIPTION, Commands.GUILD_ADMIN_TELEPORT_USAGE, Commands.GUILD_ADMIN_TELEPORT_PERMISSION, Commands.GUILD_ADMIN_TELEPORT_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1)
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));

        Guild g = GuildManager.getGuild(args[0]);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);

        p.teleport(g.getCuboid().getCenter());
        return Util.sendMessage(p, Messages.TELEPORT_END);
    }
}
