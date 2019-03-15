package net.argania.core.commands;

import net.argania.core.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Ta komenda nie jest dla konsoli");
            return false;
        }
        if (!commandSender.hasPermission("argania.core.enchant")) {
            commandSender.sendMessage(ChatUtil.fixColor("&8>> &4Blad: &cNie masz dostepu do &7(argania.core.enchant)"));
            return false;
        }

            if (strings.length < 1) {
                p.sendMessage(ChatUtil.fixColor("&8&m-----&8( &cLISTA ENCHANTOW &8)&m----"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant sharpness <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant digspeed <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant fortune <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant silktouch <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant punch <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant protection <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant knockback <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant fireaspect <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant unbreaking <level>"));
                p.sendMessage(ChatUtil.fixColor("&8&m-----&8( &cLISTA ENCHANTOW &8)&m----"));

        } else if (strings.length == 2) {

            try {
                ItemStack item = p.getItemInHand();
                int enchant_level = Integer.parseInt(strings[1]);
                String enchant_name = strings[0];

                if (enchant_name.equalsIgnoreCase("sharpness")) {
                    item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, enchant_level);
                } else if(enchant_name.equalsIgnoreCase("unbreaking")) {
                    item.addUnsafeEnchantment(Enchantment.DURABILITY, enchant_level);
                }else if(enchant_name.equalsIgnoreCase("protection")) {
                    item.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, enchant_level);
                }else if(enchant_name.equalsIgnoreCase("silktouch")){
                    item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, enchant_level);
                } else if (enchant_name.equalsIgnoreCase("knockback")) {
                    item.addUnsafeEnchantment(Enchantment.KNOCKBACK, enchant_level);
                } else if (enchant_name.equalsIgnoreCase("fireaspect")) {
                    item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, enchant_level);
                } else if(enchant_name.equalsIgnoreCase("digspeed")) {
                    item.addUnsafeEnchantment(Enchantment.DIG_SPEED, enchant_level);
                }else  if(enchant_name.equalsIgnoreCase("fortune")) {
                    item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, enchant_level);
                }else if(enchant_name.equalsIgnoreCase("punch")){
                    item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, enchant_level);
                }

                p.sendMessage(ChatUtil.fixColor("&8>> &cPoprawnie enchantowano &7" + enchant_name + " &cna level &7" + enchant_level));
                return false;
            } catch (NumberFormatException e) {
                p.sendMessage(ChatUtil.fixColor("&8&m-----&8( &cLISTA ENCHANTOW &8)&m----"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant sharpness <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant digspeed <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant fortune <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant silktouch <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant punch <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant protection <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant knockback <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant fireaspect <level>"));
                p.sendMessage(ChatUtil.fixColor("&8>> &c/enchant unbreaking <level>"));
                p.sendMessage(ChatUtil.fixColor("&8&m-----&8( &cLISTA ENCHANTOW &8)&m----"));

            }
        }
        return false;
    }
}
