package pl.awaymc.tools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.awaymc.tools.utils.ChatUtil;

public class CommandItemShop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(!sender.hasPermission("awaymc.core.is")){
            sender.sendMessage("jd");
        }
        if(args.length < 1){
            sender.sendMessage(ChatUtil.fixColor("&8&m------&8( &cITEMSHOP &8)&m------"));
            sender.sendMessage(ChatUtil.fixColor("&8>> &c/is <nick> vip &7Zakup rangi &6vip"));
            sender.sendMessage(ChatUtil.fixColor("&8>> &c/is <nick> svip &7Zakup rangi &3svip"));
            sender.sendMessage(ChatUtil.fixColor("&8>> &c/is <nick> sponsor &7Zakup rangi &5sponsor"));
            sender.sendMessage(ChatUtil.fixColor("&8&m------&8( &cITEMSHOP &8)&m------"));
        }
        if(args[1].equalsIgnoreCase("vip")){
            sender.sendMessage(ChatUtil.fixColor("&8&m------&8( &cITEMSHOP &8)&m------"));
            sender.sendMessage("");
            sender.sendMessage(ChatUtil.fixColor("&8>> &7Uzytkownik &c" + args[0] + " &7zakupil range &6vip"));
            sender.sendMessage(ChatUtil.fixColor("&8>> &7dziekujemy za wspracie na www.awaymc.pl"));
            sender.sendMessage("");
            sender.sendMessage(ChatUtil.fixColor("&8&m------&8( &cITEMSHOP &8)&m------"));
        }
        return false;
    }
}
