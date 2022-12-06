package command;

import command.customeCommand.OfficeCommand;
import command.customeCommand.RegistrationCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandContainerTests {

    @Test
    public void testGettingOfficeCommand(){
        Assertions.assertEquals(OfficeCommand.class,CommandFactory.getInstance().getCommand("user").getClass());
    }

    @Test
    public void testGettingRegistrationCommand(){
        Assertions.assertEquals(RegistrationCommand.class,CommandFactory.getInstance().getCommand("registration").getClass());
    }

    @Test
    public void wrongCommand(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> CommandFactory.getInstance().getCommand("wrong"));
    }
}
