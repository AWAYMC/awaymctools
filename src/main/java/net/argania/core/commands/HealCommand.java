package net.argania.core.commands;

import net.argania.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Ta komenda nie jest dla konsoli");
            return false;
        }
        if (!commandSender.hasPermission("argania.core.heal")) {
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &4Blad: &cNie masz dostepu do &7(argania.core.heal)"));
            return false;
        }
        if(strings.length==0){
            Player p = (Player) commandSender;
            p.setHealth(20);
            p.setFoodLevel(20);
            p.setFireTicks(0);
            p.sendMessage(ChatUtil.fixColor("&8>> &CZostales uleczony &4pomyslnie"));
            return true;
        }
        if(strings.length==1) {
            Player cel = Bukkit.getPlayerExact(strings[0]);
            if (cel != null) {
                cel.setHealth(20);
                cel.setFoodLevel(20);
                cel.setFireTicks(0);
                cel.sendMessage(ChatUtil.fixColor("&cZostales uleczony przez &7" + commandSender.getName()));
                commandSender.sendMessage(ChatUtil.fixColor("&cUleczyles uzytkownika &7" + cel.getName()));
                return true;
            } else {
                commandSender.sendMessage(ChatUtil.fixColor("&8>> &4Blad: &cPodany gracz jest Offline"));
                return false;
            }
        }else{
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &4Blad: &cPoprawne uzycie: &7/heal <nick>"));
            return false;
        }
    }
}
