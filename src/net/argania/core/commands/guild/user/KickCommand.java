package net.argania.core.commands.guild.user;

import net.argania.core.objects.guild.Guild;
import net.argania.core.objects.user.User;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.NameTagManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class KickCommand extends SubCommand {

    public KickCommand() {
        super(Commands.GUILD_USER_KICK_NAME, Commands.GUILD_USER_KICK_DESCRIPTION, Commands.GUILD_USER_KICK_USAGE, Commands.GUILD_USER_KICK_PERMISSION, Commands.GUILD_USER_KICK_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        if (!g.isLeader(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$LEADER);
        }
        OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
        User u = UserManager.getUser(op);
        if (g.isLeader(u.getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_CANT_KICK$LEADER$OR$OWNER);
        }
        if (!g.removeMember(u.getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);
        }
        NameTagManager.leaveFromGuild(g, op);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_KICKED, g, op));
    }
}
