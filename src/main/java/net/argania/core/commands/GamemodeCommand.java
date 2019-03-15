package net.argania.core.commands;

import net.argania.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
        if(cmd.getName().equalsIgnoreCase("gm")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatUtil.fixColor("&4Nie dla konsoli!"));
                return false;
            }
            if(!(sender.hasPermission("argania.core.gamemode"))) {
                sender.sendMessage(ChatUtil.fixColor("&4Blad: &cNie masz uprawien! (argania.core.gamemode)"));
                return false;
            }
            if(args.length ==1) {
                if(args[0].equals("0") || args[0].equalsIgnoreCase("survival")) {
                    Player p = (Player) sender;
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cSURVIVAL"));
                    return true;
                }
                if(args[0].equals("1") || args[0].equalsIgnoreCase("creative")) {
                    Player p = (Player) sender;
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cCREATIVE"));
                    return true;
                }
                if(args[0].equals("2") || args[0].equalsIgnoreCase("adventure")) {
                    Player p = (Player) sender;
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cADVENTURE"));
                    return true;
                }
                if(args[0].equals("3") || args[0].equalsIgnoreCase("spectator")) {
                    Player p = (Player) sender;
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cSPECTATOR"));
                    return true;
                }
            }
            if(args.length ==2) {
                Player cel = Bukkit.getPlayerExact(args[0]);
                if(cel!=null) {
                    if(args[0].equals("0") || args[0].equalsIgnoreCase("survival")) {
                        cel.setGameMode(GameMode.SURVIVAL);
                        cel.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cSURVIVAL"));
                        sender.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cSURVIVAL"));
                        return true;
                    }
                    if(args[0].equals("1") || args[0].equalsIgnoreCase("creative")) {
                        cel.setGameMode(GameMode.CREATIVE);
                        cel.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cCREATIVE"));
                        sender.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cCREATIVE"));
                        return true;
                    }
                    if(args[0].equals("2") || args[0].equalsIgnoreCase("adventure")) {
                        cel.setGameMode(GameMode.ADVENTURE);
                        cel.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cADVENTURE"));
                        sender.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cADVENTURE"));
                        return true;
                    }
                    if(args[0].equals("3") || args[0].equalsIgnoreCase("spectator")) {
                        cel.setGameMode(GameMode.SPECTATOR);
                        cel.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cSPECTATOR"));
                        sender.sendMessage(ChatUtil.fixColor("&8>> &7Tryb gry zostal zmieniony na &cSPECTATOR"));
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatUtil.fixColor("&4Blad: &cGracz jest offline!"));
                    return false;
                }
            } else {
                sender.sendMessage(ChatUtil.fixColor("&4Blad: &cPoprawne uzycie: /gm <0/1/2/3> <nick>"));
                return false;
            }
        }
        return false;
    }

}

