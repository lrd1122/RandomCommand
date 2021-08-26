package gx.lrd1122;

import gx.lrd1122.ConfigReader.LoadCommands;
import gx.lrd1122.PerformCommand.ForConsole;
import gx.lrd1122.PerformCommand.ForOp;
import gx.lrd1122.PerformCommand.ForPlayer;
import gx.lrd1122.Util.CalculateString;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandExecute implements CommandExecutor {
    private String prefix = RandomCommand.messageconfig.getString("Prefix");
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length <= 0) {
            commandSender.sendMessage(ColorString("&c=========" + prefix + "&a指令帮助" + "&c========" +
                    "\n&e/rc reward <commandname> (player) 触发一个随机指令包 rc.use" +
                    "\n&e/rc reload 重载插件 rc.reload"));
            return true;
        }
        if (strings.length == 1 && strings[0].equalsIgnoreCase("reload") && commandSender.hasPermission("rc.reload")) {
            try {
                RandomCommand.plugin.reloadConfig();
                RandomCommand.config = RandomCommand.plugin.getConfig();
                File messagefile = new File(RandomCommand.plugin.getDataFolder(), "message.yml");
                RandomCommand.messageconfig = YamlConfiguration.loadConfiguration(messagefile);
                LoadCommands.LoadCommands();
                commandSender.sendMessage(ColorString(prefix + "&a已重载插件"));
            } catch (Exception e) {
                commandSender.sendMessage(ColorString(prefix + RandomCommand.messageconfig.get("ReloadError")));
            }
            return true;
        }
        if (strings[0].equalsIgnoreCase("reward") && commandSender.hasPermission("rc.use"))
        {
            Player player = strings.length == 4 ? Bukkit.getPlayer(strings[2]) : (Player) commandSender;
            CommandBox box = CommandBox.getCommandBox(strings[1]);
            if(box == null)
            {
                player.sendMessage(ColorString(prefix + "&c未知的指令包"));
                return true;
            }
            int all = 0;
            List<CalculateBox> calculateBox = new ArrayList<>();
            for(int i  =0 ; i < box.getCommands().size(); i++)
            {
                String[] sp = box.getCommands().get(i).split(";");
                CalculateBox box1 = new CalculateBox();
                box1.setLine(i);
                box1.setMinpercentage(all);
                String papi = PlaceholderAPI.setPlaceholders(player, sp[0]);
                Double value = CalculateString.getResult(papi);
                all += Math.round(value);
                box1.setMaxpercentage(all);
                calculateBox.add(box1);
            }
            Random random = new Random();
            int randomint = random.nextInt(all);
            for(int c = 0 ; c < calculateBox.size(); c++)
            {
                CalculateBox boxa = calculateBox.get(c);
                if(randomint > boxa.getMinpercentage() && randomint < boxa.getMaxpercentage())
                {
                    String[] com = box.getCommands().get(boxa.getLine()).split(";");
                    for(int e = 1 ; e < com.length; e++)
                    {
                        String[] commandprocess = com[e].split(":");
                        if(commandprocess[0].equalsIgnoreCase("player"))
                        {
                            String replace = PlaceholderAPI.setPlaceholders(player, commandprocess[1]);
                            ForPlayer.PlayerPerform(player, replace);
                        }
                        if(commandprocess[0].equalsIgnoreCase("op"))
                        {
                            String replace = PlaceholderAPI.setPlaceholders(player, commandprocess[1]);
                            ForOp.OpPerform(player, replace);
                        }
                        if(commandprocess[0].equalsIgnoreCase("console"))
                        {
                            String replace = PlaceholderAPI.setPlaceholders(player, commandprocess[1]);
                            ForConsole.ConsolePerform(replace);
                        }
                    }
                }
            }
        }
        return true;
    }

    public List<String> getcommands(Player player, String name)
    {
        ConfigurationSection commandlist = RandomCommand.config.getConfigurationSection("CommandList");
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
