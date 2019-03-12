package net.argania.core.data;

import net.argania.core.GuildPlugin;
import net.argania.core.Utils.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabScheme {

    public static final Map<Integer, Map<Integer, String>> tablist = new HashMap<>();

    public static final Map<Integer, List<Integer>> update = new HashMap<>();

    private static final File file = new File(GuildPlugin.getPlugin().getDataFolder(), "tablist.yml");
    private static FileConfiguration tablistYaml = null;

    private static void loadTablist() {
        GuildPlugin.getPlugin().saveResource("tablist.yml", false);
        tablistYaml = YamlConfiguration.loadConfiguration(file);
        for (String column : tablistYaml.getConfigurationSection("tablist.").getKeys(false)) {
            ConfigurationSection columnSection = tablistYaml.getConfigurationSection("tablist." + column);
            Map<Integer, String> cells = new HashMap<>();
            for (String cell : columnSection.getKeys(false)) {
                cells.put(Integer.parseInt(cell), columnSection.getString(cell));
            }
            tablist.put(Integer.parseInt(column), cells);
        }
        for (String column : tablistYaml.getConfigurationSection("update-slots").getKeys(false)) {
            List<Integer> slots = tablistYaml.getIntegerList("update-slots." + column);
            update.put(Integer.parseInt(column), slots);
        }
    }

    private static void saveTablist() {
        try {
            if (file.exists()) {
                return;
            }
            tablistYaml.save(file);
        } catch (Exception e) {
            Logger.exception(e);
        }
    }

    public static void reloadTablist() {
        loadTablist();
        saveTablist();
    }

}
