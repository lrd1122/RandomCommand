package gx.lrd1122.PerformCommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class ForOp {
    public static void OpPerform(Player player, String command)
    {
        if(player.isOp())
        {
            ForPlayer.PlayerPerform(player, command);
        }
        else
        {
            player.setOp(true);
            player.performCommand(command);
            player.setOp(false);
        }
    }
    public static void OpPerform(Player player, List<String> command)
    {
        if(player.isOp())
        {
            ForPlayer.PlayerPerform(player, command);
        }
        else
        {
            player.setOp(true);
            for(int i  =0 ; i < command.size(); i++) {
                player.performCommand(command.get(i));
            }
            player.setOp(false);
        }
    }
}
