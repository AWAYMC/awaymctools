package net.argania.core.commands.guild.admin;

import net.argania.core.objects.guild.Cuboid;
import net.argania.core.objects.guild.Guild;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.utils.Util;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetCuboidCommand extends SubCommand {

    public SetCuboidCommand() {
        super(Commands.GUILD_ADMIN_SET$CUBOID_NAME, Commands.GUILD_ADMIN_SET$CUBOID_DESCRIPTION, Commands.GUILD_ADMIN_SET$CUBOID_USAGE, Commands.GUILD_ADMIN_SET$CUBOID_PERMISSION, Commands.GUILD_ADMIN_SET$CUBOID_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1)
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$ENOUGH$ARGS, this));

        Guild g = GuildManager.getGuild(args[0]);

        if (g == null)
            return Util.sendMessage(p, Messages.ERROR_CANT$FIND_GUILD);

        Location l = p.getLocation();

        if (!GuildManager.canCreateGuild(l))
            return Util.sendMessage(p, Messages.ERROR_CANT_SET$CUBOID);

        Cuboid o = g.getCuboid();
        GuildManager.removeGuildRoom(g);

        Cuboid n = new Cuboid(l.getWorld().getName(), l.getBlockX(), l.getBlockZ(), o.getSize());
        g.setCuboid(n);
        g.setHome(l);
        g.update(false);
        GuildManager.setGuildRoom(g);
        p.teleport(g.getCuboid().getCenter());
        return Util.sendMessage(p, Messages.INFO_SET_CUBOID);
    }
}
