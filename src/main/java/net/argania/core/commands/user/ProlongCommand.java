package net.argania.core.commands.user;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.utils.ItemUtil;
import net.argania.core.utils.Util;
import net.argania.core.utils.enums.Time;

public class ProlongCommand extends SubCommand {

    public ProlongCommand() {
        super(Commands.GUILD_USER_PROLONG_NAME, Commands.GUILD_USER_PROLONG_DESCRIPTION, Commands.GUILD_USER_PROLONG_USAGE, Commands.GUILD_USER_PROLONG_PERMISSION, Commands.GUILD_USER_PROLONG_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        long t = g.getExpireTime();
        if ((t - System.currentTimeMillis()) >= Time.DAY.getTime(Config.TIME_MAX)) {
            return Util.sendMessage(p, Messages.ERROR_CANT_PROLONG);
        }
        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_PROLONG_VIP : Config.ITEMS_PROLONG_NORMAL, 1);
        if (!ItemUtil.checkAndRemove(items, p)) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));
        }
        g.addExpireTime(Time.DAY.getTime(Config.TIME_ADD));
        return Util.sendMessage(p, Messages.INFO_PROLONGED$VALIDITY);
    }
}
