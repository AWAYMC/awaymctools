package net.argania.core.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.utils.Util;

public class UnBanCommand extends SubCommand {

    public UnBanCommand() {
        super(Commands.GUILD_ADMIN_UNBAN_NAME, Commands.GUILD_ADMIN_UNBAN_DESCRIPTION, Commands.GUILD_ADMIN_UNBAN_USAGE, Commands.GUILD_ADMIN_UNBAN_PERMISSION, Commands.GUILD_ADMIN_UNBAN_ALIASES);
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
        if (!g.unban()) {
            return Util.sendMessage(p, Messages.ERROR_GUILD_DONT$HAVE$BAN);
        }
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_ADMIN_UNBANNED, g, p));
    }
}
