package me.oz1p.oshamebanrecode.Utils;

import me.oz1p.oshamebanrecode.OShameBan;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
public class ShameBan {
    private int tick;
    public ShameBan(String[] args, Player plr, CommandSender sender){
        tick = OShameBan.getCfg().getInt("shame-time");
        if(OShameBan.getCfg().getInt("args") == 3){
            //Plr, time, reason
            String time = args[1];
            StringBuilder reason = new StringBuilder();
            for(int i = 2; i < args.length; i++){
                reason.append(args[i]).append(" ");
            }
            processShame(plr);
            Bukkit.getScheduler().runTaskLater(OShameBan.getInstance(), () -> {
               if(plr.isOnline()){
                   OShameBan.getCurrentShamed().remove(plr.getUniqueId());
                   if(OShameBan.getCfg().getBool("create-explosion")){
                       plr.getWorld().createExplosion(plr.getLocation().getX(), plr.getLocation().getY(), plr.getLocation().getZ(), 4.0F, false, false);
                   }
                   plr.setHealth(0);
               }
                Bukkit.dispatchCommand(sender,OShameBan.getCfg().getString("ban-command").replace("%player%", plr.getName()).replace("%time%", time).replace("%reason%", reason));
            }, tick);
        }
        if(OShameBan.getCfg().getInt("args") == 2){
            //Plr, reason
            StringBuilder reason = new StringBuilder();
            for(int i = 1; i < args.length; i++){
                reason.append(args[i]).append(" ");
            }
            processShame(plr);
            Bukkit.getScheduler().runTaskLater(OShameBan.getInstance(), () -> {
                if(plr.isOnline()){
                    OShameBan.getCurrentShamed().remove(plr.getUniqueId());
                    if(OShameBan.getCfg().getBool("create-explosion")){
                        plr.getWorld().createExplosion(plr.getLocation().getX(), plr.getLocation().getY(), plr.getLocation().getZ(), 4.0F, false, false);
                    }
                    plr.setHealth(0);
                }
                Bukkit.dispatchCommand(sender,OShameBan.getCfg().getString("ban-command").replace("%player%", plr.getName()).replace("%reason%", reason));
            }, tick);
        }
        if(OShameBan.getCfg().getInt("args") == 1){
            //Plr
            processShame(plr);
            Bukkit.getScheduler().runTaskLater(OShameBan.getInstance(), () -> {
                if(plr.isOnline()){
                    OShameBan.getCurrentShamed().remove(plr.getUniqueId());
                    if(OShameBan.getCfg().getBool("create-explosion")){
                        plr.getWorld().createExplosion(plr.getLocation().getX(), plr.getLocation().getY(), plr.getLocation().getZ(), 4.0F, false, false);
                    }
                    plr.setHealth(0);
                }
                Bukkit.dispatchCommand(sender,OShameBan.getCfg().getString("ban-command").replace("%player%", plr.getName()));
            }, tick);
        }
    }
    private void processShame(Player plr) {
        Color color;
        switch (OShameBan.getCfg().getString("effect-color").toUpperCase()) {
            case "RED":
                color = Color.RED;
                break;
            case "AQUA":
                color = Color.AQUA;
                break;
            case "BLUE":
                color = Color.BLUE;
                break;
            case "BLACK":
                color = Color.BLACK;
                break;
            case "FUCHSIA":
                color = Color.FUCHSIA;
                break;
            case "GRAY":
                color = Color.GRAY;
                break;
            case "GREEN":
                color = Color.GREEN;
                break;
            case "LIME":
                color = Color.LIME;
                break;
            case "MAROON":
                color = Color.MAROON;
                break;
            case "NAVY":
                color = Color.NAVY;
                break;
            case "OLIVE":
                color = Color.OLIVE;
                break;
            case "ORANGE":
                color = Color.ORANGE;
                break;
            case "PURPLE":
                color = Color.PURPLE;
                break;
            case "SILVER":
                color = Color.SILVER;
                break;
            case "TEAL":
                color = Color.TEAL;
                break;
            case "WHITE":
                color = Color.WHITE;
                break;
            case "YELLOW":
                color = Color.YELLOW;
                break;
            default:
                color = Color.RED;
                break;
        }
        plr.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, tick, 2));
        if(OShameBan.getCfg().getBool("play-beacon-sound")){
            for(Player player : plr.getWorld().getPlayers()){
                if(player.getLocation().distance(plr.getLocation()) <= OShameBan.getCfg().getInt("sound-distance")){
                    player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
                }
            }
        }
        new BukkitRunnable() {
            private final int numSpirals = OShameBan.getCfg().getInt("spirals-amount");
            private final double radius = OShameBan.getCfg().getDouble("effect-radius");
            private final double speed = Math.PI / 10;
            private double[] angles = new double[numSpirals];
            private int tt = 0;
            @Override
            public void run() {
                if (plr.getHealth() == 0 || plr.isDead() || !plr.isOnline()) {
                    cancel();
                    return;
                }
                double baseY = plr.getLocation().getY();
                for (int i = 0; i < numSpirals; i++) {
                    double initialAngleOffset = (2 * Math.PI * i) / numSpirals;
                    double currentAngle = angles[i] + initialAngleOffset;
                    double x = plr.getLocation().getX() + radius * Math.cos(currentAngle);
                    double z = plr.getLocation().getZ() + radius * Math.sin(currentAngle);
                    double y = baseY + OShameBan.getCfg().getDouble("y-Offset");
                    plr.getLocation().getWorld().spawnParticle(Particle.REDSTONE, x, y, z, 1, 0, 0, 0, 1, new Particle.DustOptions(color, 1));
                    angles[i] += speed;
                }
                tt++;
                if(tt >= tick){
                    cancel();
                }
            }
        }.runTaskTimer(OShameBan.getInstance(), 0, 1);
    }

}
