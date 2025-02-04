package eu.duckrealm.azingoCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static eu.duckrealm.azingoCore.AzingoCore.parseMinimessage;

public class PayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage("You must be a player to execute this command.");
            return true;
        }

        if(strings.length != 2) {
            player.sendMessage(parseMinimessage("Usage<dark_gray>:</dark_gray> <green>/pay</green> <aqua><player></aqua> <red><amount></red>"));
            return true;
        }

        Player target = player.getServer().getPlayer(strings[0]);
        if(target == null) {
            player.sendMessage(parseMinimessage("<red>Player not found."));
            return true;
        }

        int amount;
        try {
            amount = Integer.parseInt(strings[1]);
        } catch(NumberFormatException e) {
            player.sendMessage(parseMinimessage("<red>Invalid amount."));
            return true;
        }

        if(amount < 0) {
            player.sendMessage(parseMinimessage("<red>Amount must be positive."));
            return true;
        }

        if(player.getTotalExperience() < amount) {
            player.sendMessage(parseMinimessage("<red>You don't have enough XP."));
            return true;
        }

        player.giveExp(-amount);
        target.giveExp(amount);
        return true;
    }
}
