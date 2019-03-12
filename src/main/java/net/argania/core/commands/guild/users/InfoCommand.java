package net.argania.core.commands.guild.users;

import net.argania.core.Utils.Util;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.objects.guild.Guild;
import org.bukkit.entity.Player;

public class InfoCommand extends SubCommand {

    public InfoCommand() {
        super(Commands.GUILD_USER_INFO_NAME, Commands.GUILD_USER_INFO_DESCRIPTION, Commands.GUILD_USER_INFO_USAGE, Commands.GUILD_USER_INFO_PERMISSION, Commands.GUILD_USER_INFO_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        Guild g = GuildManager.getGuild(args[0]);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);
        }
        return Util.sendMessage(p, Messages.parse(Messages.INFO_GUILD, g));
    }
}
