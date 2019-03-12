package net.argania.core.commands.guild.admin;

import net.argania.core.GuildPlugin;
import net.argania.core.Utils.Util;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Messages;
import net.argania.core.data.TabScheme;
import net.argania.core.tablist.update.TabThread;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {

    public ReloadCommand() {
        super(Commands.GUILD_ADMIN_RELOAD_NAME, Commands.GUILD_ADMIN_RELOAD_DESCRIPTION, Commands.GUILD_ADMIN_RELOAD_USAGE, Commands.GUILD_ADMIN_RELOAD_PERMISSION, Commands.GUILD_ADMIN_RELOAD_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        GuildPlugin.getRevoConfig().reloadConfiguration();
        GuildPlugin.getRevoMessages().reloadConfiguration();
        GuildPlugin.getRevoCommands().reloadConfiguration();
        TabScheme.reloadTablist();
        TabThread.restart();
        return Util.sendMessage(p, Messages.INFO_RELOADED);
    }
}
