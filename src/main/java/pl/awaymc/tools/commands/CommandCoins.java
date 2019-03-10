package pl.awaymc.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.awaymc.tools.utils.ChatUtil;
import pl.awaymc.tools.utils.ItemBuilder;

public class CommandCoins implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatUtil.fixColor("&4Nie dla konsoli!"));
            return false;
        }
        if(!(sender.hasPermission("awaymc.coins"))){
            sender.sendMessage(ChatUtil.fixColor("&4Blad: &cNie masz uprawien (awaymc.coins)"));
            return false;
        }
        if (args.length < 2) {
            sender.sendMessage(ChatUtil.fixColor("&4Blad: &cPoprawne uzycie: /coins <all/gracz> <ilosc>"));
            return false;
        }
        if (!ChatUtil.isInteger(args[1])) {
            return true;
        }
        final Player player = (Player)sender;
        final int a = Integer.parseInt(args[1]);
        final ItemBuilder item = new ItemBuilder(Material.DOUBLE_PLANT, a).setTitle(ChatUtil.fixColor("&3COINS"));
        if (args[0].equalsIgnoreCase("all")) {
            if (args.length < 2) {
                sender.sendMessage(ChatUtil.fixColor("&4Blad: &cPoprawne uzycie: /coins <all/gracz> <ilosc>"));
                return true;
            }
            for (final Player online : Bukkit.getOnlinePlayers()) {
                ChatUtil.giveItems(online, item.build());
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Kazdy gracz otrzymal COINS w ilosci &8(&c" + a + "&8)"));
            }
            return true;
        }
        else {
            if (args.length < 2) {
                sender.sendMessage(ChatUtil.fixColor("&4Blad: &cPoprawne uzycie: /coins <all/gracz> <ilosc>"));
                return true;
            }
            final Player o = Bukkit.getPlayer(args[0]);
            if (o == null) {
                sender.sendMessage(ChatUtil.fixColor("&4Blad: &cGracz jest offline!"));
                return true;
            }
            ChatUtil.giveItems(o, item.build());
            o.sendMessage(ChatUtil.fixColor("&8>> &7Otrzymales COINS w ilosci &8(&c" + a + "&8)"));
            return false;
        }
        return false;
    }
}
