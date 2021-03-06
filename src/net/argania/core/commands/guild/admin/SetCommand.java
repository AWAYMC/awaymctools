package net.argania.core.commands.guild.admin;

import net.argania.core.objects.guild.Guild;
import net.argania.core.objects.user.User;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.managers.user.UserManager;
import net.argania.core.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SetCommand extends SubCommand {

    public SetCommand() {
        super(Commands.GUILD_ADMIN_SET_NAME, Commands.GUILD_ADMIN_SET_DESCRIPTION, Commands.GUILD_ADMIN_SET_USAGE, Commands.GUILD_ADMIN_SET_PERMISSION, Commands.GUILD_ADMIN_SET_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 3) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }
        Guild g = GuildManager.getGuild(args[0]);
        User user;
        switch (args[1]) {
            case "leader":
                user = UserManager.getUser(Bukkit.getPlayer(args[2]));
                if (user == null)
                    return Util.sendMessage(p, Messages.ERROR_CANT$FIND_USER);
                assert g != null;
                if (!g.isMember(user.getUuid()))
                    return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);
                g.setLeader(user.getUuid());
                break;
            case "owner":
                user = UserManager.getUser(Bukkit.getPlayer(args[2]));
                if (user == null)
                    return Util.sendMessage(p, Messages.ERROR_CANT$FIND_USER);
                assert g != null;
                if (!g.isMember(user.getUuid()))
                    return Util.sendMessage(p, Messages.ERROR_PLAYER$ISNT_MEMBER);
                g.setOwner(user.getUuid());
                break;
            case "lives":
                if (!Util.isInteger(args[2]))
                    return Util.sendMessage(p, Messages.ERROR_BAD$INTEGER);
                int lives = Integer.parseInt(args[2]);
                if (lives < 1 || lives > Config.UPTAKE_LIVES_MAX)
                    return Util.sendMessage(p, Messages.ERROR_BAD$INTEGER);
                assert g != null;
                g.setLives(lives);
                break;
            case "size":
                if (!Util.isInteger(args[1]))
                    return Util.sendMessage(p, Messages.ERROR_BAD$INTEGER);

                int v = Integer.parseInt(args[1]);

                if (v < Config.CUBOID_SIZE_START || v > Config.CUBOID_SIZE_MAX)
                    return Util.sendMessage(p, Messages.ERROR_BAD$INTEGER);

                assert g != null;
                g.getCuboid().setSize(v);
                g.update(false);
                break;
            case "pvp":
                boolean pvp = Util.getBoolean(args[2]);
                assert g != null;
                g.setPvp(pvp);
                g.update(false);
                break;
            default:
                return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));
        }

        return Util.sendMessage(p, Messages.parse(Messages.INFO_CHANGED_GUILD, g).replace("{FIELD}", args[1].toLowerCase()));
    }
}
