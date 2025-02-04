package eu.duckrealm.azingoCore.listeners;

import eu.duckrealm.azingoCore.AzingoCore;
import eu.duckrealm.azingoCore.util.DeathInventoryManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location deathLocation = player.getLocation().clone().add(0, 1, 0);
        deathLocation.setPitch(0);

        player.sendMessage(AzingoCore.parseMinimessage("<red>You died at <gold><u>" + deathLocation.getBlockX() + "</u> <u>" + deathLocation.getBlockY() + "</u> <u>" + deathLocation.getBlockZ() + "</u></gold>"));

        event.setShouldDropExperience(false);
        event.setKeepInventory(true);

        deathLocation.getWorld().spawn(deathLocation, ItemDisplay.class, entity -> {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
            skullMeta.setOwningPlayer(player);
            head.setItemMeta(skullMeta);
            entity.setItemStack(head);

            PersistentDataContainer container = entity.getPersistentDataContainer();
            container.set(new NamespacedKey(AzingoCore.instance, "XP"), PersistentDataType.INTEGER, player.getTotalExperience());

            String inventory = DeathInventoryManager.addInventory(player.getInventory().getContents());
            container.set(new NamespacedKey(AzingoCore.instance, "INVENTORY"), PersistentDataType.STRING, inventory);

            container.set(new NamespacedKey(AzingoCore.instance, "PLAYER"), PersistentDataType.STRING, player.getUniqueId().toString());

            entity.setPersistent(true);

            player.getInventory().clear();
        });
    }
}
