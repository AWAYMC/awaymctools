package net.argania.core.commands.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.NameTagManager;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.objects.user.User;
import net.argania.core.utils.Util;

public class LeaveCommand extends SubCommand {

    public LeaveCommand() {
        super(Commands.GUILD_USER_LEAVE_NAME, Commands.GUILD_USER_LEAVE_DESCRIPTION, Commands.GUILD_USER_LEAVE_USAGE, Commands.GUILD_USER_LEAVE_PERMISSION, Commands.GUILD_USER_LEAVE_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        User u = UserManager.getUser(p);
        if (g.isOwner(u.getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_OWNER$CANT$LEAVE$GUILD);
        }
        if (g.isLeader(u.getUuid())) {
            g.setOwner(g.getOwner());
        }
        g.removeMember(u.getUuid());
        NameTagManager.leaveFromGuild(g, p);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_LEAVED, g, p));
    }
}
