package gx.lrd1122;

import gx.lrd1122.ConfigReader.LoadCommands;
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
        saveDefaultConfig();
        File messagefile = new File(getDataFolder(), "message.yml");
        if(messagefile.exists()) {
            getLogger().info("message.yml文件已成功加载");
        }
        else {
            saveResource("message.yml", true);
            getLogger().info("载入message.yml文件");
        }
        Plugin placeholderapi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        if(placeholderapi == null)
        {
            getLogger().info("软前置 Placeholderapi 未安装");
        }
        plugin = this;
        config = getConfig();
        messageconfig = YamlConfiguration.loadConfiguration(messagefile);
        LoadCommands.LoadCommands();
        PluginCommand command = Bukkit.getPluginCommand("randomcommand");
        CommandExecute handle = new CommandExecute();
        command.setExecutor(handle);
        getLogger().info("插件已成功加载,意见反馈+QQ1794325461");
    }

    @Override
    public void onDisable() {
        getLogger().info("插件已成功卸载,意见反馈+QQ1794325461");
    }
}
