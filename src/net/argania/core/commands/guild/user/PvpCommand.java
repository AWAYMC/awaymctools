package net.argania.core.commands.guild.user;

import net.argania.core.objects.guild.Guild;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.NameTagManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.utils.Util;
import org.bukkit.entity.Player;

public class PvpCommand extends SubCommand {

    public PvpCommand() {
        super(Commands.GUILD_USER_PVP_NAME, Commands.GUILD_USER_PVP_DESCRIPTION, Commands.GUILD_USER_PVP_USAGE, Commands.GUILD_USER_PVP_PERMISSION, Commands.GUILD_USER_PVP_ALIASES);
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
        g.setPvp(!g.isPvp());
        g.update(false);
        for (Player o : g.getOnlineMembers()) {
            Util.sendMessage(o, g.isPvp() ? Messages.INFO_PVP_ON : Messages.INFO_PVP_OFF);
        }
        NameTagManager.setPvp(g);
        return true;
    }
}
