package eu.duckrealm.azingoCore.listeners;

import eu.duckrealm.azingoCore.AzingoCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onPreJoin(PlayerJoinEvent event) {
        //if (event.getPlayer().hasPermission("azingocore.op.bypasslock")) return;
        Player player = event.getPlayer();
        if (AzingoCore.isServerLocked() && !AzingoCore.getLockedPlayers().contains(player.getUniqueId().toString())) {
            player.kick(AzingoCore.parseMinimessage("<red>The server is currently locked!"), PlayerKickEvent.Cause.PLUGIN);
        }
    }
}
