package me.simplecoder.nolootsteal;

import me.simplecoder.nolootsteal.commands.Lootsteal;
import me.simplecoder.nolootsteal.listener.PlayerListener;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoLootSteal extends JavaPlugin {

    public static NoLootSteal plugin;
    public List<Player> spamProt = new ArrayList<>();

    public static NoLootSteal get() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        log("================================");
        log("NoLootSteal has been enabled!");

        this.saveDefaultConfig();

        registerEvents();

        registerCommands();
        log("================================");
    }

    @Override
    public void onDisable() {
        log("[NoLootSteal] Disabling.");
    }

    public void log(String msg) {
        this.getLogger().info(msg);
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
    }

    private void registerCommands() {
        getCommand("lootsteal").setExecutor(new Lootsteal());
    }
}
