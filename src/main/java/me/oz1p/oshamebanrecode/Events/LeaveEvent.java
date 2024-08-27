package me.oz1p.oshamebanrecode.Events;

import me.oz1p.oshamebanrecode.OShameBan;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {
    @EventHandler
    public void plrLeaveEvent(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if (OShameBan.getCurrentShamed().contains(player.getUniqueId())) {
            OShameBan.getCurrentShamed().remove(player.getUniqueId());
            if(OShameBan.getCfg().getBool("create-explosion")){
                player.getWorld().createExplosion(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 4.0F, false, false);
            }
            player.setHealth(0);
        }
    }
}
