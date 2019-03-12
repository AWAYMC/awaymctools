package net.argania.core.commands.guild.users;

import net.argania.core.Utils.ItemUtil;
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
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LeaderCommand extends SubCommand {

    public LeaderCommand() {
        super(Commands.GUILD_USER_LEADER_NAME, Commands.GUILD_USER_LEADER_DESCRIPTION, Commands.GUILD_USER_LEADER_USAGE, Commands.GUILD_USER_LEADER_PERMISSION, Commands.GUILD_USER_LEADER_ALIASES);
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
        if (!g.isOwner(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$OWNER);
        }
        Player o = Bukkit.getPlayer(args[0]);
        User u = UserManager.getUser(o);
        if (!g.isMember(u.getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);
        }
        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_LEADER_VIP : Config.ITEMS_LEADER_NORMAL, 1);
        if (!ItemUtil.checkAndRemove(items, p)) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));
        }
        g.setLeader(u.getUuid());
        g.update(false);
        Util.sendMessage(p, Messages.INFO_CHANGED_LEADER);
        return Util.sendMessage(o, Messages.INFO_IS$NOW_LEADER);
    }
}
