package eu.duckrealm.azingoCore;

import eu.duckrealm.azingoCore.commands.BalanceCommand;
import eu.duckrealm.azingoCore.commands.DiscordCommand;
import eu.duckrealm.azingoCore.commands.PayCommand;
import eu.duckrealm.azingoCore.listeners.PlayerDeathListener;
import eu.duckrealm.azingoCore.listeners.PlayerMoveListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AzingoCore extends JavaPlugin {
    public static AzingoCore instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        registerCommand("discord", new DiscordCommand());
        registerCommand("balance", new BalanceCommand());
        registerCommand("pay", new PayCommand());

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
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
}
