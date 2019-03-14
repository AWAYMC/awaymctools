package net.argania.core.commands.user;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.AllianceManager;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.objects.guild.Guild;
import net.argania.core.utils.ItemUtil;
import net.argania.core.utils.Util;

public class AllianceCommand extends SubCommand {

    public AllianceCommand() {
        super(Commands.GUILD_USER_ALLIANCE_NAME, Commands.GUILD_USER_ALLIANCE_DESCRIPTION, Commands.GUILD_USER_ALLIANCE_USAGE, Commands.GUILD_USER_ALLIANCE_PERMISSION, Commands.GUILD_USER_ALLIANCE_ALIASES);
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
            return Util.sendMessage(p, Messages.ERROR_YOU$ARENT$LEADER);
        }
        Guild o = GuildManager.getGuild(args[0]);
        if (o == null) {
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);
        }
        if (AllianceManager.hasAlliance(g, o)) {
            AllianceManager.removeAlliance(g, o);
            return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_ALLIANCE_DELETED, g, o));
        }
        if (AllianceManager.getGuildAlliances(g).size() >= Config.ALLIANCES$MAX) {
            return Util.sendMessage(p, Messages.ERROR_ALLIANCES$MAX);
        }
        if (AllianceManager.getInvites().contains(o.getTag() + ":" + g.getTag())) {
            List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_ALLIANCE_VIP : Config.ITEMS_ALLIANCE_NORMAL, 1);
            if (!ItemUtil.checkAndRemove(items, p)) {
                return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));
            }
            AllianceManager.getInvites().remove(o.getTag() + ":" + g.getTag());
            AllianceManager.createAlliance(g, o);
            return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_ALLIANCE_CREATED, g, o));
        }
        OfflinePlayer owner = Bukkit.getOfflinePlayer(o.getOwner());
        if (!owner.isOnline()) {
            return Util.sendMessage(p, Messages.ERROR_OWNER$NOT$ONLINE);
        }
        AllianceManager.getInvites().add(g.getTag() + ":" + o.getTag());
        Util.sendMessage(owner.getPlayer(), Messages.parse(Messages.INFO_ALLY_NEW, g));
        return Util.sendMessage(p, Messages.parse(Messages.INFO_ALLY_SEND, o));

    }
}
