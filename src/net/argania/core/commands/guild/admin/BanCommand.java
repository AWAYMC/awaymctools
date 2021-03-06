package net.argania.core.commands.guild.admin;

import net.argania.core.objects.guild.Guild;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.utils.DateUtil;
import net.argania.core.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BanCommand extends SubCommand {

    public BanCommand() {
        super(Commands.GUILD_ADMIN_BAN_NAME, Commands.GUILD_ADMIN_BAN_DESCRIPTION, Commands.GUILD_ADMIN_BAN_USAGE, Commands.GUILD_ADMIN_BAN_PERMISSION, Commands.GUILD_ADMIN_BAN_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length < 3) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        Guild g = GuildManager.getGuild(args[0]);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);
        }
        long time = DateUtil.parseDateDiff(args[1], true);
        String reason = StringUtils.join(args, " ", 2, args.length);
        String admin = p.getName();
        if (!g.ban(admin, reason, time)) {
            return Util.sendMessage(p, Messages.ERROR_GUILD_HAVE$BAN);
        }
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_ADMIN_BANNED, g, p));
    }
}
