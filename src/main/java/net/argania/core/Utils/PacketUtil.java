package net.argania.core.Utils;

import org.bukkit.entity.Player;

public class PacketUtil {

    private static final Reflection.MethodInvoker handleMethod;
    private static final Reflection.MethodInvoker sendPacket;
    private static final Reflection.FieldAccessor<Object> playerConnection;
    private static final Reflection.FieldAccessor<Integer> lastPing;

    static {
        handleMethod = Reflection.getMethod(Reflection.getCraftBukkitClass("entity.CraftEntity"), "getHandle");
        sendPacket = Reflection.getMethod(Reflection.getMinecraftClass("PlayerConnection"), "sendPacket", Reflection.getMinecraftClass("Packet"));
        playerConnection = Reflection.getSimpleField(Reflection.getMinecraftClass("EntityPlayer"), "playerConnection");
        lastPing = Reflection.getField(Reflection.getMinecraftClass("EntityPlayer"), "ping", int.class);
    }

    public static void sendPacket(Player player, Object... objects) {
        if (handleMethod == null) {
            throw new RuntimeException("HandleMethod can not be null!");
        }
        Object handle = handleMethod.invoke(player);
        for (Object o : objects) {
            sendPacket.invoke(playerConnection.get(handle), o);
        }
    }

    public static int getPing(Player p) {
        return lastPing.get(handleMethod.invoke(p));
    }

}
