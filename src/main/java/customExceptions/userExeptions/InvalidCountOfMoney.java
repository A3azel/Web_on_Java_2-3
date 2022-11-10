package customExceptions.userExeptions;

public class InvalidCountOfMoney extends Throwable{
    public InvalidCountOfMoney() {
        super();
    }

    public InvalidCountOfMoney(String message) {
        super(message);
    }
}
