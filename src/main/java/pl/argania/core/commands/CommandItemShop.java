package pl.argania.core.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.argania.core.Utils.ChatColorUtils;

public class CommandItemShop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(!sender.hasPermission("argania.core.is")) {
            TitleAPI.sendTitle(p, 20, 50, 20, ChatColorUtils.colored("&4BLAD"), ChatColorUtils.colored("&cNie masz dostepu do tej komendy"));
            return false;
        }
        return false;
    }
}
