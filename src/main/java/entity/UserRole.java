package entity;

public enum UserRole {
    USER(Names.USER),
    ADMIN(Names.ADMIN);

    public static class Names{
        public static final String USER = "User";
        public static final String ADMIN = "Admin";
    }

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
