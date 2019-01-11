package pl.awaymc.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.awaymc.tools.Main;

public class CommandFeed implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("feed")) {
            if(!(sender instanceof Player)) {
                if(args.length == 1) {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if(t != null) {
                        t.setFoodLevel(20);
                        t.sendMessage(Main.prefix + " §aZostales nasycony!");
                        sender.sendMessage(Main.prefix + " §aNasyciles gracza §c" + t.getName() + "§a!");
                    }
                    else sender.sendMessage(Main.prefix + " §4Ten gracz jest offline!");
                }
                else sender.sendMessage(Main.prefix + " §4Poprawne uzycie: §6/feed <nick>");
            }
            else {
                Player p = (Player)sender;
                if(args.length == 0) {
                    if(p.hasPermission("awaytools.feed")) {
                        p.setFoodLevel(20);
                        p.sendMessage(Main.prefix + " §aPomyslnie nasycono!");
                    }
                    else p.sendMessage(Main.prefix + " §4Nie masz uprawnien do tej komendy! §8{awaytools.feed}");
                }
                else if (args.length == 1) {
                    if(p.hasPermission("awaytools.feed.others")) {
                        Player t = Bukkit.getPlayerExact(args[0]);
                        if(t != null) {
                            t.setFoodLevel(20);
                            t.sendMessage(Main.prefix + " §aZostales nasycony przez §c" + p.getName() + "§a!");
                            p.sendMessage(Main.prefix + " §aPomyslnie nasycono gracza §c" + t.getName() + "§a!");
                        }
                        else p.sendMessage(Main.prefix + " §4Ten gracz jest offline!");
                    }
                    else p.sendMessage(Main.prefix + " §4Nie masz uprawnien do tej komendy! §8{awaytools.feed.others}");
                }
                else p.sendMessage(Main.prefix + " §4Poprawne uzycie: §6/feed <nick>");
            }
        }
        return false;
    }

}
