package eu.duckrealm.azingoCore.commands;

import eu.duckrealm.azingoCore.AzingoCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BalanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage("You must be a player to execute this command.");
            return true;
        }

        int balance = player.getTotalExperience();
        player.sendMessage(AzingoCore.parseMinimessage("<green>Your balance is: <gold><u>" + balance + "</u> XP</gold></green>"));
        return true;
    }
}
