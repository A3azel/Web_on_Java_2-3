package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class User extends BasedEntity implements Serializable {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String userEmail;
    private BigDecimal userCountOfMoney;
    private boolean accountVerified;
    private UserRole userRole;

    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public BigDecimal getUserCountOfMoney() {
        return userCountOfMoney;
    }

    public void setUserCountOfMoney(BigDecimal userCountOfMoney) {
        this.userCountOfMoney = userCountOfMoney;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return accountVerified == user.accountVerified && Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(userEmail, user.userEmail) && Objects.equals(userCountOfMoney, user.userCountOfMoney) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, password, userEmail, userCountOfMoney, accountVerified, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userCountOfMoney=" + userCountOfMoney +
                ", accountVerified=" + accountVerified +
                ", userRole=" + userRole.toString() +
                '}';
    }
}
