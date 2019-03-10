package pl.awaymc.tools.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandItemShop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(!sender.hasPermission("awaymc.core.is")){
            TitleAPI.sendTitle(p,20,50,20,ChatUtil.fixColor(""), ChatUtil.fixColor(""));
        }
        if(args.length < 1){
            TitleAPI.sendTitle(p,20,50,20,ChatUtil.fixColor("&4INFO"), ChatUtil.fixColor("&7Czytaj czat"));
            sender.sendMessage("");
        }
        return false;
    }
}
