package clinic.members;

public class Doctor extends User {

    public Doctor(String username, String password) {
        super(username, password);
        this.setRole(Role.DOCTOR);
    }

}
