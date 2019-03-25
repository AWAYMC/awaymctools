package net.argania.core.commands.guild.admin;

import net.argania.core.objects.guild.Guild;
import net.argania.core.objects.user.User;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.utils.Util;
import org.bukkit.entity.Player;

public class KickCommand extends SubCommand {

    public KickCommand() {
        super(Commands.GUILD_ADMIN_KICK_NAME, Commands.GUILD_ADMIN_KICK_DESCRIPTION, Commands.GUILD_ADMIN_KICK_USAGE, Commands.GUILD_ADMIN_KICK_PERMISSION, Commands.GUILD_ADMIN_KICK_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 2)
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));

        Guild g = GuildManager.getGuild(args[0]);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);

        User u = UserManager.getUserByName(args[1]);

        if (u == null)
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_USER);

        if (!g.isMember(u.getUuid()))
            return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);

        if (g.isOwner(u.getUuid()))
            return Util.sendMessage(p, Messages.ERROR_CANT_KICK$LEADER$OR$OWNER);

        if (g.isLeader(u.getUuid()))
            g.setLeader(g.getOwner());

        g.removeMember(u.getUuid());

        return Util.sendMessage(p, Messages.INFO_USER$KICKED);
    }
}
