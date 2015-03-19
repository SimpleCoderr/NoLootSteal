package me.simplecoder.nolootsteal.listener;

import me.simplecoder.nolootsteal.NoLootSteal;
import me.simplecoder.nolootsteal.util.MessageUtil;
import me.simplecoder.nolootsteal.util.TaskUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;


public class PlayerListener implements Listener {

    static NoLootSteal plugin = NoLootSteal.get();

    @EventHandler(priority = EventPriority.HIGH)
    public void onLootstealDeath(PlayerDeathEvent e) {
        final Player killer = e.getEntity().getKiller();
        final Player killed = e.getEntity().getPlayer();

        if (killer == null) {
            return;
        }

        for (ItemStack stack : e.getDrops()) {
            Entity entity = killed.getWorld().dropItemNaturally(killed.getLocation(), stack);
            String time = String.valueOf(System.currentTimeMillis());
            entity.setMetadata("AntiLoot", new FixedMetadataValue(plugin, killer.getName() + " " + time));
        }

        e.getDrops().clear();
        TaskUtil.startKillerMessageTimer(killer, killed);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLootstealPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        int configtime = plugin.getConfig().getInt("timeinseconds") * 1000;

        if (Boolean.valueOf(e.getItem().hasMetadata("AntiLoot")).booleanValue()) {
            String getvalue = ((MetadataValue) e.getItem().getMetadata("AntiLoot").get(0)).asString();

            String[] theValue = getvalue.split(" ");

            String killersName = theValue[0];
            Long oldTime = Long.valueOf(theValue[1]);

            if (p.getName().equals(killersName)) {
                return;
            }

            if (System.currentTimeMillis() - oldTime.longValue() > configtime) {
                return;
            }

            e.setCancelled(true);

            if (!plugin.spamProt.contains(p)) {
                p.sendMessage(MessageUtil.translateToColorCode(plugin.getConfig().getString("message")).replaceAll("KILLER", killersName));
                TaskUtil.antiSpam(p);
            }
        }
    }
}

