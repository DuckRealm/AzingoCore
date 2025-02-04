package eu.duckrealm.azingoCore.commands;

import eu.duckrealm.azingoCore.util.XPHelper;
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

        if(target.equals(player)) {
            player.sendMessage(parseMinimessage("<red>You can't pay yourself."));
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

        if(XPHelper.getPoints(player) < amount) {
            player.sendMessage(parseMinimessage("<red>You don't have enough XP."));
            return true;
        }

        player.giveExp(-amount);
        player.sendMessage(parseMinimessage("<green>You paid <red><u>" + amount + "</u> XP</red> to <aqua><u>" + target.getName() + "</u></aqua>"));
        target.giveExp(amount);
        target.sendMessage(parseMinimessage("<green>You received <red><u>" + amount + "</u> XP</red> from <aqua><u>" + player.getName() + "</u></aqua>"));
        return true;
    }
}
