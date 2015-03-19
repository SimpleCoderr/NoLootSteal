package me.simplecoder.nolootsteal.util;

import me.simplecoder.nolootsteal.NoLootSteal;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TaskUtil {

    static NoLootSteal plugin = NoLootSteal.get();

    public static void startKillerMessageTimer(final Player killer, final Player killed) {
        plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                killer.sendMessage(MessageUtil.translateToColorCode(plugin.getConfig().getString("messagefree")).replaceAll("PLAYER", killed.getName()));
            }
        }, plugin.getConfig().getInt("timeinseconds") * 20);
    }

    /**
     * AntiSpam
     *
     * @param p
     */
    public static void antiSpam(final Player p) {
        plugin.spamProt.add(p);

        plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                plugin.spamProt.remove(p);
            }
        }, plugin.getConfig().getInt("timeantispam") * 20);
    }

}

