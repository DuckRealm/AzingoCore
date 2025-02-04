package eu.duckrealm.azingoCore.listeners;

import eu.duckrealm.azingoCore.AzingoCore;
import eu.duckrealm.azingoCore.util.DeathInventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        event.getPlayer().getNearbyEntities(2, 2, 2).stream()
                .filter(entity -> entity.getType().equals(EntityType.ITEM_DISPLAY))
                .map(entity -> (ItemDisplay) entity)
                .forEach(entity -> {
                    String playerUUID = entity.getPersistentDataContainer().get(new NamespacedKey(AzingoCore.instance, "PLAYER"), PersistentDataType.STRING);

                    if(!player.getUniqueId().toString().equals(playerUUID)) return;

                    String inventoryUUID = entity.getPersistentDataContainer().get(new NamespacedKey(AzingoCore.instance, "INVENTORY"), PersistentDataType.STRING);
                    ItemStack[] inventory = DeathInventoryManager.getInventory(inventoryUUID);
                    if(inventory != null) player.getInventory().setContents(inventory);
                    String xp = entity.getPersistentDataContainer().get(new NamespacedKey(AzingoCore.instance, "XP"), PersistentDataType.STRING);

                    if(xp == null) {
                        AzingoCore.instance.getLogger().severe("XP not found for player " + player.getName() + " " + player.getLocation());
                        player.sendMessage(AzingoCore.parseMinimessage("<red>Something went wrong. Please report to an admin."));
                        entity.remove();
                        DeathInventoryManager.removeInventory(inventoryUUID);
                        return;
                    }
                    
                    //xp = "level:progress"
                    int level = Integer.parseInt(xp.split(":")[0]);
                    float progress = Float.parseFloat(xp.split(":")[1]);
                    player.setLevel(level);
                    player.setExp(progress);
                    player.playSound(player.getLocation(), "minecraft:entity.player.levelup", 1, 1);
                    entity.remove();
                    DeathInventoryManager.removeInventory(inventoryUUID);
                });
    }
}
