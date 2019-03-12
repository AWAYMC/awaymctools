package net.argania.core.commands.ranking;

import net.argania.core.Utils.Util;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.objects.users.User;
import net.argania.core.tablist.RankList;
import net.argania.core.tablist.update.TabThread;
import org.bukkit.entity.Player;

public class TopCommand extends SubCommand {

    public TopCommand() {
        super(Commands.TOP_NAME, Commands.TOP_DESCRIPTION, Commands.TOP_USAGE, Commands.TOP_PERMISSION, Commands.TOP_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Util.sendMessage(p, Messages.LIST_RANKING_HEADER);
        int i = 1;
        for (RankList.Data<User> u : TabThread.getInstance().getRankList().getTopPlayers()) {
            if (i > 10) {
                break;
            }
            Util.sendMessage(p, Messages.parse(Messages.LIST_RANKING_ELEMENT, u.getKey()).replace("{POS}", Integer.toString(i)));
            i++;
        }
        return Util.sendMessage(p, Messages.LIST_RANKING_FOOTER);

    }
}
