package net.argania.core.commands.guild.users;


import net.argania.core.Utils.Util;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.users.UserManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.objects.users.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class InviteCommand extends SubCommand {

    public InviteCommand() {
        super(Commands.GUILD_USER_INVITE_NAME, Commands.GUILD_USER_INVITE_DESCRIPTION, Commands.GUILD_USER_INVITE_USAGE, Commands.GUILD_USER_INVITE_PERMISSION, Commands.GUILD_USER_INVITE_ALIASES);
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
        if (g.getMembers().size() >= Config.MEMBERS$MAX) {
            return Util.sendMessage(p, Messages.ERROR_GUILD_IS$FULL);
        }
        Player o = Bukkit.getPlayer(args[0]);
        User oU = UserManager.getUser(o);
        if (g.isMember(oU.getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_PLAYER$IS_MEMBER);
        }
        if (!g.addInvite(oU.getUuid())) {
            g.removeInvite(oU.getUuid());
            Util.sendMessage(p, Messages.parse(Messages.INFO_INVITE_BACK, o));
            return Util.sendMessage(o, Messages.parse(Messages.INFO_INVITE_CANCEL, g));
        }
        Util.sendMessage(p, Messages.parse(Messages.INFO_INVITE_SEND, o));
        return Util.sendMessage(o, Messages.parse(Messages.INFO_INVITE_NEW, g));
    }

}
