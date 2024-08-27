package me.oz1p.oshamebanrecode.Events;

import me.oz1p.oshamebanrecode.OShameBan;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
       if(OShameBan.getCfg().getBool("disable-movement")){
           Player player = e.getPlayer();
           if (OShameBan.getCurrentShamed().contains(player.getUniqueId())) {
               Location from = e.getFrom();
               Location to = e.getTo();
               if (to != null) {
                   e.setTo(new Location(to.getWorld(), from.getX(), to.getY(), from.getZ(), to.getYaw(), to.getPitch()));
               }
           }
       }
    }
}
