package net.argania.core.commands;

import net.argania.core.Utils.Reflection;
import net.argania.core.Utils.Util;
import net.argania.core.commands.guild.GuildAdminCommand;
import net.argania.core.commands.guild.GuildCommand;
import net.argania.core.commands.guild.users.TopCommand;
import net.argania.core.commands.ranking.RankingAdminCommand;
import net.argania.core.commands.ranking.RankingCommand;
import net.argania.core.data.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class SubCommand extends Command {

    private static final HashMap<String, Command> commands = new HashMap<>();
    private static final Reflection.FieldAccessor<SimpleCommandMap> f = Reflection.getField(SimplePluginManager.class, "commandMap", SimpleCommandMap.class);
    private static CommandMap cmdMap = f.get(Bukkit.getServer().getPluginManager());
    private static String fallback;

    private final String name;
    private final String usage;
    private final String desc;
    private final String permission;

    protected SubCommand(String name, String desc, String usage, String permission, String... aliases) {
        super(name, desc, usage, Arrays.asList(aliases));
        this.name = name;
        this.usage = usage;
        this.desc = desc;
        this.permission = permission;
    }

    protected SubCommand(String name, String desc, String usage, String permission, List<String> aliases) {
        super(name, desc, usage, aliases);
        this.name = name;
        this.usage = usage;
        this.desc = desc;
        this.permission = permission;
    }

    public static void registerCommand(GuildAdminCommand guildCommand) {
    }

    public static void registerCommand(TopCommand rankingAdminCommand) {
    }

    public static void registerCommand(RankingCommand rankingCommand) {
    }

    public static void registerCommand(RankingAdminCommand rankingAdminCommand) {
    }

    public static void registerCommand(GuildCommand guildCommand) {
    }

    public static void registerCommand(CombatCommand combatCommand) {
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return Util.sendMessage(sender, "&cYou must be a player to run that command!");
        }
        Player p = (Player) sender;
        if (!p.hasPermission(this.permission)) {
            return Util.sendMessage(p, Messages.parse(Messages.COMMANDS_NO$PERMISSIONS, this));
        }
        return onCommand(p, args);
    }

    public static void registerCommand(RevoGuildCommand cmd) {
        cmdMap.register(fallback, cmd);
        commands.put(cmd.getName(), cmd);
    }

    public abstract boolean onCommand(Player player, String[] args);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsage() {
        return usage;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String getPermission() {
        return permission;
    }

}
