package gx.lrd1122.PerformCommand;

import org.bukkit.entity.Player;

import java.util.List;

public class ForPlayer {
    public static void PlayerPerform(Player player, String command)
    {
        player.performCommand(command);
    }
    public static void PlayerPerform(Player player, List<String> command)
    {
        for(int i = 0 ; i<command.size(); i++)
        {
            player.performCommand(command.get(i));
        }
    }
}
