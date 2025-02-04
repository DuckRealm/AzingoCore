package eu.duckrealm.azingoCore;

import eu.duckrealm.azingoCore.commands.DiscordCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AzingoCore extends JavaPlugin {
    public static AzingoCore instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Objects.requireNonNull(getCommand("discord")).setExecutor(new DiscordCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Component parseMinimessage(String message) {
        return MiniMessage.miniMessage().deserialize(message);
    }
}
