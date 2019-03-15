package net.argania.core.commands;

import net.argania.core.managers.ChatManager;
import net.argania.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("argania.core.chat")) {
            sender.sendMessage(ChatUtil.fixColor("&4Blad: &7Nie masz dostepu do tej komendy! &7(argania.core.chat)"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatUtil.fixColor("&8>> &7Poprawne uzycie: &3/chat (on|off|cc)"));
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "wyczysc":
            case "clear":
            case "cc": {
                for (int i = 0; i < 100; ++i) Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Chat zostal wyczyszczony!"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Przez: &3" + sender.getName() + "!"));
                return true;
            }
            case "wylacz":
            case "off": {
                if (!ChatManager.isEnabled()) {
                    sender.sendMessage(ChatUtil.fixColor("&4Blad: &cChat jest juz wylaczony!"));
                    return true;
                }
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Chat zostal wylaczony!"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Przez: &3" + sender.getName() + "!"));
                ChatManager.setEnabled(false);
                return true;
            }
            case "wlacz":
            case "on": {
                if (ChatManager.isEnabled()) {
                    sender.sendMessage(ChatUtil.fixColor("&4Blad: &cChat jest juz wlaczony!"));
                    return true;
                }
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Chat zostal wlaczony!"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Przez: &3" + sender.getName() + "!"));
                ChatManager.setEnabled(true);
                return true;
            }
        }
        return false;
    }
}
