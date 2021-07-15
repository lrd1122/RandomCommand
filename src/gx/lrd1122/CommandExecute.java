package gx.lrd1122;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.Random;

public class CommandExecute implements CommandExecutor {
    private String prefix = RandomCommand.messageconfig.getString("Prefix");
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = strings.length == 2 ? Bukkit.getPlayer(strings[1]) : (Player) commandSender;
        commandSender = player;
        if(strings.length <= 0)
        {
            commandSender.sendMessage(ColorString("&c&l========="+prefix+"&a&l指令帮助"+"&c&l========" +
                    "\n&e&l/rc <commandname> <player> 触发一个随机指令包 rc.use" +
                    "\n&e&l/rc reload 重载插件 rc.reload"));
            return true;
        }
        if(strings.length >= 1 && player.hasPermission("rc.use"))
        {
            try {
                int fixpercent = 0;
                List<String> commands = getcommands(player, strings[0]);
                for (int i = 0; i < commands.size(); i++) {
                    String[] splitcommand = commands.get(i).split(",");
                    String percentpapi = PlaceholderAPI.setPlaceholders(player, splitcommand[0]);
                    float percent = Float.valueOf(percentpapi);
                    fixpercent += (int)percent;
                }
                Random random = new Random();
                int percentnow = random.nextInt(fixpercent);
                for (int i = 0; i < commands.size(); i++) {
                    if (i > 0) {
                        String[] splitcommanda = commands.get(i).split(",");
                        String[] splitcommandb = commands.get(i - 1).split(",");
                        String currentapapi = PlaceholderAPI.setPlaceholders(player, splitcommanda[0]);
                        String currentbpapi = PlaceholderAPI.setPlaceholders(player, splitcommandb[0]);
                        float currenta = Float.valueOf(currentapapi);
                        float currentb = Float.valueOf(currentbpapi);
                        int fixcurrenta = (int)currenta;
                        int fixcurrentb = (int)currentb;
                        int max = fixcurrenta + fixcurrentb;
                        if (rangeint(percentnow, fixcurrenta, max)) {
                            if(splitcommanda[1].equalsIgnoreCase("player"))
                            {
                                for(int a = 2; a < splitcommanda.length; a++) {
                                    player.performCommand(splitcommanda[a]);
                                }
                            }
                            else if(splitcommanda[1].equalsIgnoreCase("op") && !player.isOp())
                            {
                                player.setOp(true);
                                for(int a = 2; a < splitcommanda.length; a++) {
                                    player.performCommand(splitcommanda[a]);
                                }
                                player.setOp(false);
                            }
                            if(splitcommanda[1].equalsIgnoreCase("op") && player.isOp())
                            {
                                for(int a = 2; a < splitcommanda.length; a++) {
                                    player.performCommand(splitcommanda[a]);
                                }
                            }
                            break;
                        }
                    }
                    if (i <= 0) {
                        String[] splitcommanda = commands.get(i).split(",");
                        String currentpapi = PlaceholderAPI.setPlaceholders(player, splitcommanda[0]);
                        float current = Float.valueOf(currentpapi);
                        int fixcurrent = (int) current;
                        if (rangeint(percentnow, 0, fixcurrent)) {
                            if(splitcommanda[1].equalsIgnoreCase("player"))
                            {
                                for(int a = 2; a < splitcommanda.length; a++) {
                                    player.performCommand(splitcommanda[a]);
                                }
                            }
                            if(splitcommanda[1].equalsIgnoreCase("op") && !player.isOp())
                            {
                                player.setOp(true);
                                for(int a = 2; a < splitcommanda.length; a++) {
                                    player.performCommand(splitcommanda[a]);
                                }
                                player.setOp(false);
                            }
                            if(splitcommanda[1].equalsIgnoreCase("op") && player.isOp())
                            {
                                for(int a = 2; a < splitcommanda.length; a++) {
                                    player.performCommand(splitcommanda[a]);
                                }
                            }
                            break;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                player.sendMessage(ColorString(prefix + RandomCommand.messageconfig.get("NoneCommands")));
            }
        }
        if(strings.length== 1 && strings[0].equalsIgnoreCase("reload") && player.hasPermission("rc.reload"))
        {
            try{
                RandomCommand.plugin.reloadConfig();
                File messagefile = new File(RandomCommand.plugin.getDataFolder(), "message.yml");
                RandomCommand.messageconfig = YamlConfiguration.loadConfiguration(messagefile);
            }
            catch (Exception e) {
                player.sendMessage(ColorString(prefix +  RandomCommand.messageconfig.get("ReloadError")));
            }
        }
        return true;
    }

    public List<String> getcommands(Player player, String name)
    {
        ConfigurationSection commandlist = RandomCommand.plugin.getConfig().getConfigurationSection("CommandList");
        return PlaceholderAPI.setPlaceholders(player, commandlist.getStringList(name));
    }
    public String ColorString(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    public static boolean rangeint(int current, int min, int max)
    {
        return Math.max(min, current) == Math.min(current, max);
    }
}
