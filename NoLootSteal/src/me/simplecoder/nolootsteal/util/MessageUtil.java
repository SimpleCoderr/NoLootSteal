package me.simplecoder.nolootsteal.util;

import org.bukkit.ChatColor;

public class MessageUtil {

    public static String translateToColorCode(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
