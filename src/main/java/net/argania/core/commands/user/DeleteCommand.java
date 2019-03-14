package net.argania.core.commands.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.argania.core.GuildPlugin;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.utils.Util;

public class DeleteCommand extends SubCommand {

    public DeleteCommand() {
        super(Commands.GUILD_USER_DELETE_NAME, Commands.GUILD_USER_DELETE_DESCRIPTION, Commands.GUILD_USER_DELETE_USAGE, Commands.GUILD_USER_DELETE_PERMISSION, Commands.GUILD_USER_DELETE_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        if (!g.isOwner(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$OWNER);
        }
        if (!g.isPreDeleted()) {
            g.setPreDeleted(true);
            Util.sendMessage(p, Messages.INFO_CONFIRM$DELETE);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (g.isPreDeleted()) {
                        g.setPreDeleted(false);
                    }
                }
            }.runTaskLater(GuildPlugin.getPlugin(), 20 * 10);
            return true;
        }
        GuildManager.removeGuild(g);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_DELETED, g));
    }

}
