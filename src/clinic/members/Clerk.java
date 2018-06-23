package clinic.members;

public class Clerk extends User {

    public Clerk(String username, String password) {
        super(username, password);
        this.setRole(Role.CLERK);
    }

}
