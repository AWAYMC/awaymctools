package pl.awaymc.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.awaymc.tools.Main;

public class CommandBroadcast implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("broadcast")) {
            if(!(sender instanceof Player)) {
                if(args.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < args.length; i++) {
                        sb.append(args[i]).append(" ");
                    }
                    String msg = sb.toString();
                    for(Player t : Bukkit.getOnlinePlayers()) {
                        t.sendMessage(Main.prefix + " " + msg);
                    }
                }
                else sender.sendMessage(Main.prefix + " §4Poprawne uzycie: §6/broadcast <wiadomosc>");
            }
            else {
                Player p = (Player)sender;
                if(p.hasPermission("awaytools.broadcast")) {
                    if(args.length != 0) {
                        StringBuilder sb = new StringBuilder();
                        for(int i = 0; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        String msg = sb.toString();
                        for(Player t : Bukkit.getOnlinePlayers()) {
                            t.sendMessage(Main.prefix + " " + msg);
                        }
                    }
                    else p.sendMessage(Main.prefix + " §4Poprawne uzycie: §6/broadcast <wiadomosc>");
                }
                else p.sendMessage(Main.prefix + " §4Nie masz uprawnien do tej komendy! §8{awaytools.broadcast}");
            }
        }
        return false;
    }

}
