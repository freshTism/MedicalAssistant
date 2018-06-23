package clinic.members;

public enum Role {

    DOCTOR("پزشک"),
    CLERK("منشی");

    private String role;

    Role(String role) { this.role = role; }

    public String getRole() { return role; }
}
