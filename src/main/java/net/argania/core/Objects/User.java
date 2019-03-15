package net.argania.core.Objects;

import net.argania.core.managers.UserManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {

    private String name;
    private UUID uuid;
    private long protectionTime;

    private User(final Player p) {
        this.name = p.getName();
        this.uuid = p.getUniqueId();
        this.protectionTime = 0L;
        UserManager.addUser(this);
    }

    private User(final UUID uuid) {
        this.uuid = uuid;
        UserManager.addUser(this);
    }

    public String getName() {
        return this.name;
    }

    public UUID getUniqueId() {
        return this.uuid;
    }

    public long getProtectionTime() {
        return this.protectionTime;

    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setUniqueId(final UUID uuid) {
        this.uuid = uuid;
    }

    public void setProtectionTime(final long protectionTime) {
        this.protectionTime = protectionTime;
    }

    public boolean isProtected() {
        return this.getProtectionTime() >= System.currentTimeMillis();
    }

    public static User get(final Player p) {
        for (final User u : UserManager.getUsers()) {
            if (u.getUniqueId().equals(p.getUniqueId())) {
                return u;
            }
        }
        return new User(p);
    }

    public static User get(final UUID uuid) {
        for (final User u : UserManager.getUsers()) {
            if (u.getUniqueId().equals(uuid)) {
                return u;
            }
        }
        return new User(uuid);
    }
}
