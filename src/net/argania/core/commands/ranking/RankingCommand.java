package net.argania.core.commands.ranking;

import net.argania.core.objects.user.User;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.user.UserManager;
import net.argania.core.utils.Util;
import org.bukkit.entity.Player;

public class RankingCommand extends SubCommand {

    public RankingCommand() {
        super(Commands.RANKING_USER_NAME, Commands.RANKING_USER_DESCRIPTION, Commands.RANKING_USER_USAGE, Commands.RANKING_USER_PERMISSION, Commands.RANKING_USER_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        User u = null;
        if (args.length == 0) {
            u = UserManager.getUser(p);
        } else if (args.length == 1) {
            u = UserManager.getUserByName(args[0]);
        }
        if (u == null) {
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_PLAYER);
        }
        return Util.sendMessage(p, Messages.parse(Messages.INFO_RANKING, u));
    }
}
