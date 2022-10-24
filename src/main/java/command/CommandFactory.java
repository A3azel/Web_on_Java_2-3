package command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static CommandFactory factory;

    private Map<String, Controller> commands = new HashMap<String, Controller>();

    private CommandFactory(){

    }

    public static synchronized CommandFactory getInstance(){
        if(factory == null){
            return new CommandFactory();
        }
        return factory;
    }


    {

    }

    public Command getCommand(String commandName) {
        if(!commands.containsKey(commandName) || commandName==null){
            throw new IllegalArgumentException("Command not found");
        }
        return commands.get(commandName);
    }


}
