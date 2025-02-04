package eu.duckrealm.azingoCore.commands;

import eu.duckrealm.azingoCore.AzingoCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        commandSender.sendMessage(AzingoCore.parseMinimessage("<green>Join our Discord: </green><u><blue><click:open_url:'https://discord.gg/MSn8Rk68D2'>https://discord.gg/MSn8Rk68D2</click></blue></u>"));
        return true;
    }
}
