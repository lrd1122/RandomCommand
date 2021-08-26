package gx.lrd1122.PerformCommand;

import org.bukkit.Bukkit;

import java.util.List;

public class ForConsole {
    public static void ConsolePerform(String command)
    {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }
    public static void ConsolePerform(List<String> command)
    {
        for(int i = 0; i < command.size(); i++) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.get(i));
        }
    }
}
