package pl.awaymc.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.awaymc.tools.Main;

public class CommandGamemode implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("gamemode")) {
            if(!(sender instanceof Player)) {
                if(args.length == 2) {
                    Player t = Bukkit.getPlayerExact(args[1]);
                    if (t != null) {
                        if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
                            t.setGameMode(GameMode.SURVIVAL);
                            sender.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6SURVIVAL �adla gracza �c" + t.getName());
                        }
                        else if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
                            t.setGameMode(GameMode.CREATIVE);
                            sender.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6CREATIVE �adla gracza �c" + t.getName());
                        }
                        else if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
                            t.setGameMode(GameMode.ADVENTURE);
                            sender.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6ADVENTURE �adla gracza �c" + t.getName());
                        }
                        else if(args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
                            t.setGameMode(GameMode.SPECTATOR);
                            sender.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6SPECATOR �adla gracza �c" + t.getName());
                        }
                        else sender.sendMessage(Main.prefix + " �4Poprawne uzycie: �6/gamemode <0/1/2/3> <nick>");
                    }
                    else sender.sendMessage(Main.prefix + " �4Ten gracz jest offline!");
                }
                else sender.sendMessage(Main.prefix + " �4Poprawne uzycie: �6/gamemode <0/1/2/3> <nick>");
            }
            else {
                Player p = (Player)sender;
                if(p.hasPermission("awaytools.gamemode")) {
                    if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6SURVIVAL");
                        }
                        else if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6CREATIVE");
                        }
                        else if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6ADVENTURE");
                        }
                        else if(args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6SPECATOR");
                        }
                        else p.sendMessage(Main.prefix + " �4Poprawne uzycie: �6/gamemode <0/1/2/3>");
                    }
                    else if(args.length == 2) {
                        Player t = Bukkit.getPlayerExact(args[1]);
                        if (t != null) {
                            if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
                                t.setGameMode(GameMode.SURVIVAL);
                                p.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6SURVIVAL �adla gracza �c" + t.getName());
                            }
                            else if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
                                t.setGameMode(GameMode.CREATIVE);
                                p.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6CREATIVE �adla gracza �c" + t.getName());
                            }
                            else if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
                                t.setGameMode(GameMode.ADVENTURE);
                                p.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6ADVENTURE �adla gracza �c" + t.getName());
                            }
                            else if(args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
                                t.setGameMode(GameMode.SPECTATOR);
                                p.sendMessage(Main.prefix + " �aPomyslnie nadano tryb gry �6SPECATOR �adla gracza �c" + t.getName());
                            }
                            else p.sendMessage(Main.prefix + " �4Poprawne uzycie: �6/gamemode <0/1/2/3> <nick>");
                        }
                        else p.sendMessage(Main.prefix + " �4Ten gracz jest offline!");
                    }
                    else p.sendMessage(Main.prefix + " �4Poprawne uzycie: �6/gamemode <0/1/2/3> <nick>");
                }
                else p.sendMessage(Main.prefix + " §4Nie masz uprawnien do tej komendy! §8{awaymc.gamemode}");
            }
        }
        return false;
    }

}
