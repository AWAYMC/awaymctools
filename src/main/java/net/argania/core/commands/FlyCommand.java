package net.argania.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
        if(cmd.getName().equalsIgnoreCase("fly")) {
            if (sender instanceof Player) {
                final Player player = (Player)sender;
                if (player.hasPermission("argania.core.fly")) {
                    if (args.length == 0) {
                        if (!player.getAllowFlight()) {
                            player.setAllowFlight(true);
                            player.sendMessage("&8>> &7Latanie zostalo &cWLACZONE");
                        }
                        else {
                            player.setAllowFlight(false);
                            player.sendMessage("&8>> &7Latanie zostalo &cWYLACZONE");
                        }
                    }
                    else if (args.length >= 1) {
                        if (player.hasPermission("argania.core.fly.other")) {
                            final Player other = Bukkit.getPlayer(args[0]);
                            if (other != null) {
                                if (!other.getAllowFlight()) {
                                    other.setAllowFlight(true);
                                    player.sendMessage("&8>> &7Latanie dla gracza &c" + other.getName() + " &7zostalo &cWLACZONE");
                                    other.sendMessage("&8>> &7Latanie zostalo &cWLACZONE &7przez administratora");
                                }
                                else {
                                    other.setAllowFlight(false);
                                    player.sendMessage("&8>> &7Latanie dla gracza &c" + other.getName() + " &7zostalo &cWYLACZONE");
                                    other.sendMessage("&8>> &7Latanie zostalo &cWYLACZONE &7przez administratora");
                                }
                            }
                            else {
                                player.sendMessage("&cGracz &7" + args[0] + " &cjest offline.");
                            }
                        }
                        else {
                            player.sendMessage("&4Blad: &cNie masz uprawien (argania.core.fly.other)");
                            return false;
                        }
                    }
                }
                else {
                    player.sendMessage("&4Blad: &cNie masz uprawien (argania.core.fly)");
                    return false;
                }
            }
            else {
                sender.sendMessage("&4To polecenie nie moze byc wykonane z konsoli.");
            }
        }
        return false;
    }
}
