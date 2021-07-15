package gx.lrd1122;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class RandomCommand extends JavaPlugin {

    public static JavaPlugin plugin;
    public static YamlConfiguration messageconfig;
    public static Configuration config;
    @Override
    public void onEnable() {
        getLogger().info("[RandomCommand] 插件已成功加载,Bug反馈+QQ1794325461");
        saveDefaultConfig();
        File messagefile = new File(getDataFolder(), "message.yml");
        Plugin placeholderapi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        if(messagefile.exists()) {
            getLogger().info("[RandomCommand] message.yml文件已成功加载");
        }
        else {
            saveResource("message.yml", true);
            getLogger().info("[RandomCommand] 载入message.yml文件");
        }
        plugin = this;
        config = getConfig();
        messageconfig = YamlConfiguration.loadConfiguration(messagefile);
        PluginCommand command = Bukkit.getPluginCommand("randomcommand");
        CommandExecute handle = new CommandExecute();
        command.setExecutor(handle);
    }

    @Override
    public void onDisable() {
        getLogger().info("[RandomCommand] 插件已成功卸载");
    }
}
