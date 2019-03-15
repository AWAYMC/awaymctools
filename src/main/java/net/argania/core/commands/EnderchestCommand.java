package net.argania.core.commands;

import net.argania.core.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        if(command.getName().equalsIgnoreCase("enderchest")) {
            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage("Ta komende moze wywolac tylko gracz");
                return false;
            }
            if(!(commandSender.hasPermission("argania.core.enderchest"))) {
                commandSender.sendMessage(ChatUtil.fixColor("&4Blad: &cNie masz uprawien (argania.core.enderchest)"));
                return false;
            }
            if(strings.length ==0) {
                Player p = (Player) commandSender;
                p.openInventory(p.getEnderChest());
            }
        }
        return false;
    }

}

