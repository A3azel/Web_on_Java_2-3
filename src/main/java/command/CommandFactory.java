package command;

import command.customeCommand.LoginCommand;
import command.customeCommand.MainCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static CommandFactory factory;

    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory(){

    }

    public static synchronized CommandFactory getInstance(){
        if(factory == null){
            return new CommandFactory();
        }
        return factory;
    }


    {
        commands.put("login",new LoginCommand());
        commands.put("trainsBetweenCities",new MainCommand());
    }

    public Command getCommand(String commandName) {
        if(!commands.containsKey(commandName) || commandName==null){
            throw new IllegalArgumentException("Command not found");
        }
        return commands.get(commandName);
    }


}
