package me.oz1p.oshamebanrecode;
import me.oz1p.oshamebanrecode.Commands.ShameExecutor;
import me.oz1p.oshamebanrecode.Commands.TabCompleter;
import me.oz1p.oshamebanrecode.Events.LeaveEvent;
import me.oz1p.oshamebanrecode.Events.MoveEvent;
import me.oz1p.oshamebanrecode.Utils.Config;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashSet;
import java.util.UUID;

public final class OShameBan extends JavaPlugin {
    private static HashSet<UUID> currentShamed;
    private static String version = "1.1 (Recode)";
    private static Config cfg;
    private static JavaPlugin instance;
    @Override
    public void onEnable() {
        getLogger().info("§c       ____  _                          ____              ");
        getLogger().info("§c   ___/ ___|| |__   __ _ _ __ ___   ___| __ )  __ _ _ __  ");
        getLogger().info("§c  / _ \\___ \\| '_ \\ / _` | '_ ` _ \\ / _ \\  _ \\ / _` | '_ \\ ");
        getLogger().info("§c | (_) |__) | | | | (_| | | | | | |  __/ |_) | (_| | | | |");
        getLogger().info("§c  \\___/____/|_| |_|\\__,_|_| |_| |_|\\___|____/ \\__,_|_| |_|");
        getLogger().info("§c                        "+version);
        getLogger().info("Загрузка...");
        // Register instance
        instance = this;
        // Config
        getLogger().info("Загрузка конфига...");
        cfg = new Config(this);
        // HashSet
        currentShamed = new HashSet<>();
        // Metrics
        getLogger().info("Загрузка BStats...");
        Metrics metrics = new Metrics(this, 23158);
        // Command
        getLogger().info("Регистрация команд...");
        getCommand("oShameBan").setExecutor(new ShameExecutor());
        getCommand("oShameBan").setTabCompleter(new TabCompleter());
        // Register events
        getLogger().info("Регистрация эвентов...");
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
        getLogger().info("Готово!");
    }
    public static JavaPlugin getInstance(){
        return instance;
    }
    public static Config getCfg(){
        return cfg;
    }
    public static HashSet<UUID> getCurrentShamed(){
        return currentShamed;
    }
    public static String getVersion(){
        return version;
    }
    @Override
    public void onDisable() {
        cfg = null;
        currentShamed.clear();
        currentShamed = null;
    instance = null;
        getLogger().info("Пока-пока!");
    }
}
