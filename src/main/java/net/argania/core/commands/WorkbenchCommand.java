package net.argania.core.commands;

import net.argania.core.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Ta komenda nie jest dla konsoli");
            return false;
        }
        if(!commandSender.hasPermission("argania.core.workbench")){
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &4Blad: &cNie masz dostepu do &7(argania.core.workbench)"));
            return false;
        }
        if(strings.length==0){
            Player p = (Player) commandSender;
            p.openInventory(p.openWorkbench();
        }
        return false;
    }
}
