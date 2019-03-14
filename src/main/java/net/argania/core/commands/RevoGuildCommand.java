package net.argania.core.commands;


import org.bukkit.entity.Player;

import net.argania.core.GuildPlugin;
import net.argania.core.data.Commands;
import net.argania.core.utils.Util;

public class RevoGuildCommand extends SubCommand {

    public RevoGuildCommand() {
        super("revoguild", "informacje o pluginie", "/revoguild", Commands.GUILD_USER_MAIN_PERMISSION);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Util.sendMessage(p, " &7&m--------&r &cRevoGUILD &7&m--------&r");
        Util.sendMessage(p, " &8» &7Wersja: &c" + GuildPlugin.getPlugin().getDescription().getVersion());
        Util.sendMessage(p, " &8» &7Strona pluginu: &chttps://github.com/userMacieG/RevoGuild");
        Util.sendMessage(p, " &8» &7Autor: &cKarolek");
        Util.sendMessage(p, " &8» &7Fork: &cuserMacieG");
        Util.sendMessage(p, " &7Nie usuwaj tej notki, szanuj czyjas prace, to bardzo mile! ;)");
        return true;
    }
}
