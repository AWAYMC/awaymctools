package net.argania.core.commands;

import net.argania.core.GuildPlugin;
import net.argania.core.Utils.Util;
import net.argania.core.data.Commands;
import org.bukkit.entity.Player;

public class RevoGuildCommand extends SubCommand {

    public RevoGuildCommand() {
        super("arganguild", "informacje o pluginie", "/arganguild", Commands.GUILD_USER_MAIN_PERMISSION);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Util.sendMessage(p, " &7&m--------&r &cArgamGUILD &7&m--------&r");
        Util.sendMessage(p, " &8» &7Wersja: &c" + GuildPlugin.getPlugin().getDescription().getVersion());
        Util.sendMessage(p, " &8» &7Strona pluginu: &chttps://argania.pl");
        Util.sendMessage(p, " &8» &7Autorzy: &cPiechuuu,Adekkk");
        Util.sendMessage(p, " &8» &7Fork: &cuserMacieG");
        Util.sendMessage(p, " &7Nie usuwaj tej notki, szanuj czyjas prace, to bardzo mile! ;)");
        return true;
    }
}
