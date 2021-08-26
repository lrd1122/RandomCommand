package gx.lrd1122;

import gx.lrd1122.ConfigReader.LoadCommands;

import java.util.List;

public class CommandBox {
    private String name;
    private List<String> commands;
    public CommandBox()
    {

    }
    public CommandBox(String name, List<String> commands)
    {
        this.name = name;
        this.commands = commands;
    }

    public List<String> getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static CommandBox getCommandBox(String name)
    {
        for(int i  =0 ; i < LoadCommands.boxList.size(); i++)
        {
            if(LoadCommands.boxList.get(i).getName().equalsIgnoreCase(name))
            {
                return LoadCommands.boxList.get(i);
            }
        }
        return null;
    }

}
