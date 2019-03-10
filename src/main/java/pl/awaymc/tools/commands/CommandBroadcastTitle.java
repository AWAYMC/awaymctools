package pl.awaymc.tools.commands;

import org.bukkit.Bukkit;
import pl.awaymc.tools.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBroadcastTitle implements CommandExecutor{

    @SuppressWarnings("deprecation")
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if(!sender.hasPermission("awaytools.broadcast.title")) {
            sender.sendMessage(Main.prefix + " §4Nie masz uprawnien do tej komendy! §8{awaytools.broadcast.title}");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Main.prefix + " §4Poprawne uzycie: §6/bct <wiadomosc>");
        } else if (args.length > 0) {

            String message = "";
            for (String part : args) {
                if (message != "") message += " ";
                message += part;
            }

            for (Player all : Bukkit.getServer().getOnlinePlayers()){
                all.sendTitle(Main.prefix, " " + message);
            }
        }
        return false;}}
