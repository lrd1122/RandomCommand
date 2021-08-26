package gx.lrd1122.ConfigReader;

import gx.lrd1122.CommandBox;
import gx.lrd1122.RandomCommand;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoadCommands {
    public static List<CommandBox> boxList;
    public static void LoadCommands()
    {
        boxList = new ArrayList<>();
        Configuration config = RandomCommand.config;
        ConfigurationSection section = config.getConfigurationSection("CommandList");
        if(section == null)
        {
            return;
        }
        Set<String> stringSet = section.getKeys(false);
        List<String> lists = new ArrayList<>(stringSet);
        for(int i = 0 ; i< lists.size(); i++) {
            CommandBox box = new CommandBox();
            box.setName(lists.get(i));
            box.setCommands(section.getStringList(lists.get(i)));
            boxList.add(box);
        }
    }
}
