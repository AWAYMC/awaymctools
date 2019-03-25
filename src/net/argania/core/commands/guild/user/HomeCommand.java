package net.argania.core.commands.guild.user;


import net.argania.core.objects.guild.Guild;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.TeleportManager;
import net.argania.core.utils.Util;
import org.bukkit.entity.Player;

public class HomeCommand extends SubCommand {

    public HomeCommand() {
        super(Commands.GUILD_USER_HOME_NAME, Commands.GUILD_USER_HOME_DESCRIPTION, Commands.GUILD_USER_HOME_USAGE, Commands.GUILD_USER_HOME_PERMISSION, Commands.GUILD_USER_HOME_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);

        TeleportManager.teleport(p, g.getHome(), Config.TIME_TELEPORT);
        return true;
    }
}
