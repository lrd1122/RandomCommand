package gx.lrd1122;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class CommandExecute implements CommandExecutor {
    private String prefix = RandomCommand.messageconfig.getString("Prefix");
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player)commandSender;
        if(strings.length <= 0)
        {
            commandSender.sendMessage(ColorString("&c&l========="+prefix+"&a&l指令帮助"+"&c&l========" +
                    "\n&e&l/rc <commandname>"));
            return true;
        }
        if(strings.length == 1)
        {
            try {
                int percent = 0;
                List<String> commands = getcommands(strings[0]);
                for (int i = 0; i < commands.size(); i++) {
                    String[] splitcommand = commands.get(i).split(",");
                    percent += Integer.valueOf(splitcommand[0]);
                }
                Random random = new Random();
                int percentnow = random.nextInt(percent);
                for (int i = 0; i < commands.size(); i++) {
                    if (i > 0) {
                        String[] splitcommanda = commands.get(i).split(",");
                        String[] splitcommandb = commands.get(i - 1).split(",");
                        int currenta = Integer.valueOf(splitcommanda[0]);
                        int currentb = Integer.valueOf(splitcommandb[0]);
                        int max = currenta + currentb;
                        if (rangeint(percentnow, currenta, max)) {
                            if(splitcommanda[1].equalsIgnoreCase("player"))
                            {
                                player.performCommand(splitcommanda[2]);
                            }
                            else if(splitcommanda[1].equalsIgnoreCase("op") && !player.isOp())
                            {
                                player.setOp(true);
                                player.performCommand(splitcommanda[2]);
                                player.setOp(false);
                            }
                            if(splitcommanda[1].equalsIgnoreCase("op") && player.isOp())
                            {
                                player.performCommand(splitcommanda[2]);
                            }
                            break;
                        }
                    }
                    if (i <= 0) {
                        String[] splitcommanda = commands.get(i).split(",");
                        if (rangeint(percentnow, 0, Integer.valueOf(splitcommanda[0]))) {
                            if(splitcommanda[1].equalsIgnoreCase("player"))
                            {
                                player.performCommand(splitcommanda[2]);
                            }
                            if(splitcommanda[1].equalsIgnoreCase("op") && !player.isOp())
                            {
                                player.setOp(true);
                                player.performCommand(splitcommanda[2]);
                                player.setOp(false);
                            }
                            if(splitcommanda[1].equalsIgnoreCase("op") && player.isOp())
                            {
                                player.performCommand(splitcommanda[2]);
                            }
                            break;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                player.sendMessage(prefix + RandomCommand.messageconfig.get("NoneCommands"));
            }
        }
        return true;
    }

    public List<String> getcommands(String name)
    {
        ConfigurationSection commandlist = RandomCommand.plugin.getConfig().getConfigurationSection("CommandList");
        return commandlist.getStringList(name);
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
