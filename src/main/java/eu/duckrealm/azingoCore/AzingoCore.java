package eu.duckrealm.azingoCore;

import eu.duckrealm.azingoCore.commands.*;
import eu.duckrealm.azingoCore.listeners.PlayerDeathListener;
import eu.duckrealm.azingoCore.listeners.PlayerMoveListener;
import eu.duckrealm.azingoCore.listeners.JoinListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class AzingoCore extends JavaPlugin {
    public static AzingoCore instance;

    private static boolean locked = false;
    //players that allowed back onto the server once locked
    //this is to prevent players from being locked out of the server if they crash or get disconnected
    private static List<String> lockedPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        registerCommand("discord", new DiscordCommand());
        registerCommand("balance", new BalanceCommand());
        registerCommand("pay", new PayCommand());
        registerCommand("lock", new LockCommand());
        registerCommand("unlock", new UnlockCommand());

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private static void registerCommand(String command, CommandExecutor executor) {
        Objects.requireNonNull(instance.getCommand(command)).setExecutor(executor);
    }

    public static Component parseMinimessage(String message) {
        return MiniMessage.miniMessage().deserialize(message);
    }

    public static Path getPath() {
        return instance.getDataFolder().toPath();
    }

    public static void setLockedPlayers() {
        lockedPlayers.clear();
        for (var player : instance.getServer().getOnlinePlayers()) {
            lockedPlayers.add(player.getUniqueId().toString());
        }
    }

    public static List<String> getLockedPlayers() {
        return lockedPlayers;
    }

    public static void clearLockedPlayers() {
        lockedPlayers.clear();
    }

    public static void setServerLocked(boolean lock) {
        locked = lock;
        if(lock) {
            setLockedPlayers();
        } else {
            clearLockedPlayers();
        }
    }
    public static boolean isServerLocked() {
        return locked;
    }
}
