package net.argania.core.commands.guild.user;

import net.argania.core.objects.guild.Guild;
import net.argania.core.objects.user.User;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.tablist.RankList.Data;
import net.argania.core.tablist.update.TabThread;
import net.argania.core.utils.Util;
import org.bukkit.entity.Player;

public class TopCommand extends SubCommand {

    public TopCommand() {
        super(Commands.GUILD_USER_TOP_NAME, Commands.GUILD_USER_TOP_DESCRIPTION, Commands.GUILD_USER_TOP_USAGE, Commands.GUILD_USER_TOP_PERMISSION, Commands.GUILD_USER_TOP_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Util.sendMessage(p, Messages.LIST_GUILD_HEADER);
        int i = 1;
        for (Data<Guild> g : TabThread.getInstance().getRankList().getTopGuilds()) {
            if (i > 10) {
                break;
            }
            Util.sendMessage(p, Messages.parse(Messages.LIST_GUILD_ELEMENT, g.getKey()).replace("{POS}", Integer.toString(i)));
            i++;
        }
        return Util.sendMessage(p, Messages.LIST_GUILD_FOOTER);
    }
}
