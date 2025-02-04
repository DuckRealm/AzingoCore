package eu.duckrealm.azingoCore.util;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class DeathInventoryManager {
    private static HashMap<String, ItemStack[]> inventories = new HashMap<>();

    public static String addInventory(ItemStack[] inventory) {
        String id = UUID.randomUUID().toString();
        inventories.put(id, inventory);
        return id;
    }

    public static ItemStack[] getInventory(String id) {
        return inventories.get(id);
    }

    public static void removeInventory(String id) {
        inventories.remove(id);
    }
}
