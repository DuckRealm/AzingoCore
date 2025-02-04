package eu.duckrealm.azingoCore.commands;

import eu.duckrealm.azingoCore.AzingoCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class LockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        AzingoCore.setServerLocked(true);
        commandSender.sendMessage(AzingoCore.parseMinimessage("<red>Server locked."));
        return true;
    }
}
