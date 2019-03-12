package pl.argania.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.argania.core.Utils.ChatColorUtils;

public class CommandItemShop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(!sender.hasPermission("argania.core.itemshop")) {
            sender.sendMessage(ChatColorUtils.colored("&4Blad: &cNie masz uprawien!"));
            return false;
        }
        if(args.length < 1) {
            sender.sendMessage(ChatColorUtils.colored("&4Blad: &cPoprawne uzycie: /itemshop <nick> <vip/unban/key>"));
            return false;
        }
        if(args[1].equalsIgnoreCase("vip")){
            Bukkit.broadcastMessage(ChatColorUtils.colored("&8----------------------------------"));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&7Gracz &c" + args[0] + " &7zakupil range&8: &cVIP"));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&cDziekujemy za wsparcie &4&l<3 "));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&fNasz ItemShop&8: &f&lWWW.ARGANIA.PL/SKLEP"));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&8----------------------------------"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rank " + args[0] + " vip 30d");
            return true;
        }
        if(args[1].equalsIgnoreCase("unban")) {
            Bukkit.broadcastMessage(ChatColorUtils.colored("&8----------------------------------"));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&7Gracz &c" + args[0] + " &7zakupil usluge&8: &cUNBAN"));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&cDziekujemy za wsparcie &4&l<3 "));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&fNasz ItemShop&8: &f&lWWW.ARGANIA.PL/SKLEP"));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&8----------------------------------"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "unban" + args[0]);
            return true;
        }
        if(args[1].equalsIgnoreCase("key")) {
            Bukkit.broadcastMessage(ChatColorUtils.colored("&8----------------------------------"));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&7Gracz &c" + args[0] + " &7zakupil usluge&8: &cKLUCZE"));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&cDziekujemy za wsparcie &4&l<3 "));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&fNasz ItemShop&8: &f&lWWW.ARGANIA.PL/SKLEP"));
            Bukkit.broadcastMessage(ChatColorUtils.colored("&8----------------------------------"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "case key " + args[0] + " 5");
            return true;
        }
        return false;
    }
}
