package me.simplecoder.nolootsteal.commands;

import me.simplecoder.nolootsteal.NoLootSteal;
import me.simplecoder.nolootsteal.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lootsteal implements CommandExecutor {

    static NoLootSteal plugin = NoLootSteal.get();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = null;

        if (sender instanceof Player) {
            p = (Player) sender;
        }

        if (commandLabel.equalsIgnoreCase("lootsteal")) {

            if (p == null) {
                plugin.log("Console is not allowed to do the command.");
                return true;
            }

            if (!p.hasPermission("lootsteal.admin")) {
                p.sendMessage(MessageUtil.translateToColorCode(plugin.getConfig().getString("prefix")) + ChatColor.RED + " Permission denied!");
                return true;
            }

            if (args.length != 1) {
                p.sendMessage(MessageUtil.translateToColorCode(plugin.getConfig().getString("prefix")) + ChatColor.RED + " Usage: /lootsteal <reload>");
                return true;
            }

            if (args.length == 1) {

                if (args[0].equalsIgnoreCase("reload")) {
                    p.sendMessage(MessageUtil.translateToColorCode(plugin.getConfig().getString("prefix")) + ChatColor.GREEN + " Configuration Has Been Reloaded!");
                    plugin.reloadConfig();
                    return true;
                }

                p.sendMessage(MessageUtil.translateToColorCode(plugin.getConfig().getString("prefix")) + ChatColor.RED + " Usage: /lootsteal <reload>");
            }
        }
        return false;
    }
}


