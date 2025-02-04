package eu.duckrealm.azingoCore.util;

import org.bukkit.entity.Player;

public class XPHelper {
    public static double getPoints(Player player) {
        double points = levelsToPoints(player.getLevel());
        points += player.getExp() * player.getExpToLevel();
        return points;
    }

    public static double levelsToPoints(int levels) {
        if(levels <= 16) {
            return Math.pow(levels, 2) + 6 * levels;
        }
        if(levels <= 31) {
            return 2.5f * Math.pow(levels, 2) - 40.5f * levels + 360;
        }
        return 4.5f * Math.pow(levels, 2) - 162.5f * levels + 2220;
    }
}
