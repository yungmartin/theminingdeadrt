package me.martin.main.Scoreboards;

import me.martin.main.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class SBManager {

    private static ScoreboardManager scoreboardManager;
    private static Scoreboard scoreboard;

    private static Objective objective;

    public static Scoreboard createScoreboard(Player player){

        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();

        objective = scoreboard.registerNewObjective("Scoreboard", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Utils.chatColor("&4&lThe Mining Dead"));

        double kd = (double) player.getStatistic(Statistic.PLAYER_KILLS) / player.getStatistic(Statistic.DEATHS);
        Score line1 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&8&l&m+-----------------+&r"));
        Score line3 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fKills: &6" + player.getStatistic(Statistic.PLAYER_KILLS) + "&c①"));
        Score line4 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fDeaths: &6" + player.getStatistic(Statistic.DEATHS) + "&r⑫"));
        Score line5 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fMoney: &a$0②"));
        Score line6 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fLevel: &7&l1"));
        Score line7 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&8&l&m+-----------------+&4"));
        Score line8 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fKD: &8(&b" + String.format("%.2f", (double) player.getStatistic(Statistic.PLAYER_KILLS) / player.getStatistic(Statistic.DEATHS)) + "&8)"));
        Score line10 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&8&l&m+-----------------+&3"));
        Score line11 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fTMD Practice &8(&b" + Bukkit.getOnlinePlayers().size() + "&b/100&8)"));
        Score line12 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "     &eminingdead.com"));
        Score line13 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&8&l&m+-----------------+&5"));
        line1.setScore(13);
        line3.setScore(12);
        line4.setScore(11);
        line5.setScore(10);
        line6.setScore(9);
        line7.setScore(8);
        line8.setScore(7);
        line10.setScore(3);
        line11.setScore(2);
        line12.setScore(1);
        line13.setScore(0);

        return scoreboard;

    }

    public static void displayScoreboard(Player player, Scoreboard scoreboard){

        player.setScoreboard(scoreboard);

    }

}
