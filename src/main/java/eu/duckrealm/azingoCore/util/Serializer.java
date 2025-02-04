package eu.duckrealm.azingoCore.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Serializer {

    public static String serializeItemStackArray(ItemStack[] items) {
        StringBuilder builder = new StringBuilder();

        for(ItemStack item : items) {
            if(item == null) {
                builder.append(";");
                continue;
            }
            builder.append(Arrays.toString(item.serializeAsBytes()));
            builder.append(";");
        }

        return builder.toString();
    }

    public static ItemStack[] deserializeItemStackArray(String serialized) {
        List<ItemStack> items = new ArrayList<>();

        for(String item : serialized.split(";")) {
            if(item.isEmpty()) items.add(new ItemStack(Material.AIR));
            else items.add(ItemStack.deserializeBytes(toByteArray(item)));
        }

        return items.toArray(new ItemStack[0]);
    }

    private static byte[] toByteArray(String string) {
        //get rid of [ and ] and split by ", "
        if(string.isEmpty()) return new byte[0];
        String[] parts = string.substring(1, string.length() - 1).split(", ");
        byte[] bytes = new byte[parts.length];
        for(int i = 0; i < parts.length; i++) {
            bytes[i] = Byte.parseByte(parts[i]);
        }
        return bytes;
    }
}
