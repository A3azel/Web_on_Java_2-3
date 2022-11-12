package command;

import command.customeCommand.*;
import command.customeCommand.adminCommand.AllRoutsCommand;
import command.customeCommand.adminCommand.AllStationsCommand;
import command.customeCommand.adminCommand.AllUsersCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static CommandFactory factory;

    private final Map<String, Command> commands = new HashMap<>();

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
        commands.put("user",new OfficeCommand());
        commands.put("order",new OrderCommand());
        commands.put("makeOrder",new MakeOrderCommand());
        commands.put("registration",new RegistrationCommand());
        commands.put("logout",new LogoutCommand());
        commands.put("topUpAccount",new TopUpAccountCommand());
        commands.put("userPurchasedTickets",new UserPurchasedTicketsCommand());
        commands.put("purchasedTicket",new OrderInfoCommand());
        commands.put("allUsers",new AllUsersCommand());
        commands.put("allStationForAdmin",new AllStationsCommand());
        commands.put("allRoutsForAdmin",new AllRoutsCommand());
    }

    public Command getCommand(String commandName) {
        if(!commands.containsKey(commandName) || commandName==null){
            throw new IllegalArgumentException("Command not found");
        }
        return commands.get(commandName);
    }


}
