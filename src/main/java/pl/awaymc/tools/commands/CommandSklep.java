package pl.awaymc.tools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.awaymc.tools.gui.SkleGUI;

public class CommandSklep implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sklep")) {
            Player p = (Player) sender;
            if (!(sender instanceof Player)) {
                sender.sendMessage("§4Komenda nie dla konsoli!");
            }
            if (!(sender.hasPermission("awaymc.sklep"))) {
                sender.sendMessage("§4Blad: §cNie masz uprawien (awaymc.sklep)");
                return false;
            }
            if(args.length ==1){
                SkleGUI.sklep(p);
            }
        }
        return false;
    }
}
