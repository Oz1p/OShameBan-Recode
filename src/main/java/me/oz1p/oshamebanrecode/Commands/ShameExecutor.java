package me.oz1p.oshamebanrecode.Commands;

import me.oz1p.oshamebanrecode.OShameBan;
import me.oz1p.oshamebanrecode.Utils.Config;
import me.oz1p.oshamebanrecode.Utils.HelpMethods;
import me.oz1p.oshamebanrecode.Utils.ShameBan;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShameExecutor implements CommandExecutor {
    private Config cfg;
    public ShameExecutor(){
        cfg = OShameBan.getCfg();
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 1 && strings[0].startsWith("reload")){
            if(commandSender.hasPermission("oshameban.reload") || commandSender.isOp()){
                cfg.reload();
                commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.reloaded"));
                return true;
            }
            commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.no-perms"));
            return true;
        }
        if(cfg.getInt("args") == 3){
            if(strings.length >= 3){
                if(commandSender.hasPermission("oshameban.use") || commandSender.isOp()){
                    Player plr = Bukkit.getPlayer(strings[0]);
                    if(plr != null){
                        OShameBan.getCurrentShamed().add(plr.getUniqueId());
                        new ShameBan(strings, plr, commandSender);
                        return true;
                    }
                    commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.player-offline").replace("%player%", strings[0]));
                    return true;
                }
                commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.no-perms"));
                return true;
            }
        }

        if(cfg.getInt("args") == 2){
            if(strings.length >= 2){
                if(commandSender.hasPermission("oshameban.use") || commandSender.isOp()){
                    Player plr = Bukkit.getPlayer(strings[0]);
                    if(plr != null){
                        OShameBan.getCurrentShamed().add(plr.getUniqueId());
                        new ShameBan(strings, plr, commandSender);
                        return true;
                    }
                    commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.player-offline").replace("%player%", strings[0]));
                    return true;
                }
                commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.no-perms"));
                return true;
            }
        }

        if(cfg.getInt("args") == 1){
            if(strings.length >= 1){
                if(commandSender.hasPermission("oshameban.use") || commandSender.isOp()){
                    Player plr = Bukkit.getPlayer(strings[0]);
                    if(plr != null){
                        OShameBan.getCurrentShamed().add(plr.getUniqueId());
                        new ShameBan(strings, plr, commandSender);
                        return true;
                    }
                    commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.player-offline").replace("%player%", strings[0]));
                    return true;
                }
                commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.no-perms"));
                return true;
            }
        }
        commandSender.sendMessage(cfg.getPrefix() + HelpMethods.processHexColors("&7Версия: " + OShameBan.getVersion()));
        commandSender.sendMessage(cfg.getPrefix() + HelpMethods.processHexColors("&7Разработчик: Oz1p"));
        commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.usage-1"));
        commandSender.sendMessage(cfg.getPrefix() + cfg.getString("lang.usage-2"));

        return true;
    }
}
