package net.argania.core.commands.guild.user;

import net.argania.core.objects.guild.Guild;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.NameTagManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.utils.ItemUtil;
import net.argania.core.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class JoinCommand extends SubCommand {

    public JoinCommand() {
        super(Commands.GUILD_USER_JOIN_NAME, Commands.GUILD_USER_JOIN_DESCRIPTION, Commands.GUILD_USER_JOIN_USAGE, Commands.GUILD_USER_JOIN_PERMISSION, Commands.GUILD_USER_JOIN_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        if (GuildManager.getGuild(p) != null) {
            return Util.sendMessage(p, Messages.ERROR_HAVE$GUILD);
        }
        Guild g = GuildManager.getGuild(args[0]);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);
        }
        String algorithm = Config.ALGORITHM_JOIN;
        algorithm = algorithm.replace("{MEMBERS_NUM}", Integer.toString(g.getMembers().size()));
        int modifier = Util.calculate(algorithm);
        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_JOIN_VIP : Config.ITEMS_JOIN_NORMAL, modifier);
        if (!ItemUtil.checkItems(items, p)) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));
        }
        if (!g.addMember(UserManager.getUser(p).getUuid())) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_INVITE);
        }
        ItemUtil.removeItems(items, p);
        Util.sendMessage(p, Messages.parse(Messages.INFO_JOINED, g));
        NameTagManager.joinToGuild(g, p);
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_JOINED, g, p));
    }

}
