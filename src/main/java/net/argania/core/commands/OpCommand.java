package net.argania.core.commands;

import net.argania.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("argania.core.op")) {
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &4Blad: &cNie masz dostepu do &7(argania.core.op)"));
        }
        if (strings.length < 1) {
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &4Blad: &cPoprawne uzycie: &7/op <nick> <nadaj|zdejmij>"));
        }
        if (strings.length < 2) {
            commandSender.sendMessage(ChatUtil.fixColor("&4Blad: &cWpisz nadaj|zdejmij!"));
            return false;
        }
        if (strings[1].equalsIgnoreCase("zdejmij")) {
            commandSender.sendMessage(ChatUtil.fixColor("&8&m----------------------------------"));
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &cUsunales opa &7" + strings[0]));
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &cPomyslnie"));
            commandSender.sendMessage(ChatUtil.fixColor("&8&m----------------------------------"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "deop " + strings[0]);
            return true;
        }
        if (strings[1].equalsIgnoreCase("nadaj")) {
            commandSender.sendMessage(ChatUtil.fixColor("&8&m----------------------------------"));
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &cNadales opa &7" + strings[0]));
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &cPomyslnie"));
            commandSender.sendMessage(ChatUtil.fixColor("&8&m----------------------------------"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + strings[0]);
            return true;

        }
        return false;
    }
}
