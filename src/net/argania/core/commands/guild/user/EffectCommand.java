package net.argania.core.commands.guild.user;

import net.argania.core.objects.guild.Guild;
import net.argania.core.commands.SubCommand;
import net.argania.core.data.Commands;
import net.argania.core.data.Config;
import net.argania.core.data.Messages;
import net.argania.core.managers.guild.GuildManager;
import net.argania.core.utils.ItemUtil;
import net.argania.core.utils.RandomUtil;
import net.argania.core.utils.TimeUtil;
import net.argania.core.utils.enums.Time;
import net.argania.core.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EffectCommand extends SubCommand {

    private static final Map<Guild, Long> times = new HashMap<>();

    public EffectCommand() {
        super(Commands.GUILD_USER_EFFECT_NAME, Commands.GUILD_USER_EFFECT_DESCRIPTION, Commands.GUILD_USER_EFFECT_USAGE, Commands.GUILD_USER_EFFECT_PERMISSION, Commands.GUILD_USER_EFFECT_ALIASES);
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_GUILD);
        }
        Long cooldown = times.get(g);
        if (cooldown != null && ((System.currentTimeMillis() - cooldown) < Time.MINUTE.getTime(Config.EFFECTS_TIME_INTERVAL))) {
            return Util.sendMessage(p, Messages.ERROR_MUST$WAIT);
        }
        List<ItemStack> items = ItemUtil.getItems(p.hasPermission("revoguild.vip") ? Config.ITEMS_EFFECT_VIP : Config.ITEMS_EFFECT_NORMAL, 1);
        if (!ItemUtil.checkAndRemove(items, p)) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_ITEMS.replace("{ITEMS}", ItemUtil.getItems(items)));
        }
        if (!RandomUtil.getChance(Config.EFFECTS_CHANCE)) {
            return Util.sendMessage(p, Messages.ERROR_DONT$HAVE_LUCKY$TO$EFFECT);
        }
        int level = RandomUtil.getRandInt(Config.EFFECTS_LEVEL_MIN, Config.EFFECTS_LEVEL_MAX);
        int time = RandomUtil.getRandInt(Config.EFFECTS_TIME_MIN, Config.EFFECTS_TIME_MAX);
        if (Config.EFFECTS_TYPES.size() <= 0) {
            return Util.sendMessage(p, Messages.ERROR_NO$EFFECTS$TO$ROLL);
        }
        String effect = Config.EFFECTS_TYPES.get(RandomUtil.getRandInt(0, Config.EFFECTS_TYPES.size() - 1));
        PotionEffect potion = new PotionEffect(PotionEffectType.getByName(effect), Time.SECOND.getTick(time), level);
        times.put(g, System.currentTimeMillis());
        for (Player player : g.getOnlineMembers()) {
            player.addPotionEffect(potion);
        }
        return Util.sendMessage(Bukkit.getOnlinePlayers(), Messages.parse(Messages.BROADCAST_GUILD_EFFECT, g).replace("{EFFECT}", effect.toLowerCase().replace("_", " ")).replace("{LEVEL}", Integer.toString(level)).replace("{TIME}", TimeUtil.secondsToString(time)));
    }
}
