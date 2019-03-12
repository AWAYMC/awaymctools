package net.argania.core.commands.guild.users;

import net.argania.core.Utils.Util;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.users.UserManager;
import net.argania.core.objects.guild.Guild;
import org.bukkit.entity.Player;

public class SetHomeCommand extends SubCommand {

    public SetHomeCommand() {
        super(Commands.GUILD_USER_SET$HOME_NAME, Commands.GUILD_USER_SET$HOME_DESCRIPTION, Commands.GUILD_USER_SET$HOME_USAGE, Commands.GUILD_USER_SET$HOME_PERMISSION, Commands.GUILD_USER_SET$HOME_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        if (!g.isOwner(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$OWNER);
        }
        Guild o = GuildManager.getGuild(p.getLocation());
        if (!g.equals(o)) {
            return Util.sendMessage(p, Messages.ERROR_CANT_SET$HOME$OUTSIDE$CUBOID);
        }
        g.setHome(p.getLocation());
        g.update(true);
        return Util.sendMessage(p, Messages.INFO_SET_HOME);
    }
}
