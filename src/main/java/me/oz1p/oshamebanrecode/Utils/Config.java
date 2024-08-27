package me.oz1p.oshamebanrecode.Utils;

import me.oz1p.oshamebanrecode.OShameBan;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private FileConfiguration config;
    private OShameBan plugin;
    public Config(OShameBan plugin){
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        plugin.getLogger().info("Конфиг загружен!");
    }
    public void reload(){
        plugin.reloadConfig();
        config = plugin.getConfig();
        plugin.getLogger().info("Конфиг перезагружен!");
    }
    public String getString(String str){
        try{
            return HelpMethods.processHexColors(config.getString(str));
        }
        catch (Exception e){
            plugin.getLogger().info("§cОшибка в конфиге (" + str + ")");
            return " §cОшибка в конфиге (" + str + ")§r ";
        }
    }
    public Boolean getBool(String str){
        try{
            return config.getBoolean(str);
        }
        catch (Exception e){
            plugin.getLogger().info("§cОшибка в конфиге (" + str + ")");
            return false;
        }
    }
    public String getPrefix(){
        return getString("prefix") + " §r";
    }
    public Integer getInt(String str){
        try{
            return config.getInt(str);
        }
        catch (Exception e){
            plugin.getLogger().info("§cОшибка в конфиге (" + str + ")");
            return 1;
        }
    }
    public Double getDouble(String str){
        try{
            return config.getDouble(str);
        }
        catch (Exception e){
            plugin.getLogger().info("§cОшибка в конфиге (" + str + ")");
            return 1.0;
        }
    }
}
