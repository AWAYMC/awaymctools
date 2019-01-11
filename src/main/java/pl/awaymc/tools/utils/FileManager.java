package pl.awaymc.tools.utils;

import org.bukkit.entity.Player;
import pl.awaymc.tools.Main;

import java.io.File;

public class FileManager {

    public static File users = new File(Main.getInst().getDataFolder(), "users");

    public static void checkFiles() {
        if(!Main.getInst().getDataFolder().exists()) {
            Main.getInst().getDataFolder().mkdir();
        }
        if(!(new File(Main.getInst().getDataFolder(), "config.yml").exists())) {
            Main.getInst().saveDefaultConfig();
        }
        if(!users.exists()) {
            users.mkdir();
        }
    }

    public static File getPFile(Player p) {
        File f = new File(users, p.getName() + ".yml");
        if(f != null) return f;
        else return null;
    }

}
