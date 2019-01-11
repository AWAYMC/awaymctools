package pl.awaymc.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.awaymc.tools.Main;

public class CommandHeal implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("heal")) {
            if(!(sender instanceof Player)) {
                if(args.length == 1) {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if(t != null) {
                        t.setHealth(t.getMaxHealth());
                        t.setFoodLevel(20);
                        t.sendMessage(Main.prefix + " §aZostales uleczony!");
                        sender.sendMessage(Main.prefix + " §aUleczyles gracza §c" + t.getName() + "§a!");
                    }
                    else sender.sendMessage(Main.prefix + " §4Ten gracz jest offline!");
                }
                else sender.sendMessage(Main.prefix + " §4Poprawne uzycie: §6/heal <nick>");
            }
            else {
                Player p = (Player)sender;
                if(args.length == 0) {
                    if(p.hasPermission("dtools.heal")) {
                        p.setHealth(p.getMaxHealth());
                        p.setFoodLevel(20);
                        p.sendMessage(Main.prefix + " §aPomyslnie uleczono!");
                    }
                    else p.sendMessage(Main.prefix + " §4Nie masz uprawnien do tej komendy!");
                }
                else if (args.length == 1) {
                    if(p.hasPermission("dtools.heal.player")) {
                        Player t = Bukkit.getPlayerExact(args[0]);
                        if(t != null) {
                            t.setHealth(t.getMaxHealth());
                            t.setFoodLevel(20);
                            t.sendMessage(Main.prefix + " §aZostales uleczony przez §c" + p.getName() + "§a!");
                            p.sendMessage(Main.prefix + " §aPomyslnie uleczono gracza §c" + t.getName() + "§a!");
                        }
                        else p.sendMessage(Main.prefix + " §4Ten gracz jest offline!");
                    }
                    else p.sendMessage(Main.prefix + " §4Nie masz uprawnien do tej komendy!");
                }
                else p.sendMessage(Main.prefix + " §4Poprawne uzycie: §6/heal <nick>");
            }
        }
        return false;
    }

}
