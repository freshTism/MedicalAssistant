package main;

import clinic.customer.Patient;
import clinic.members.Clerk;
import clinic.members.Doctor;
import clinic.members.Role;

public abstract class Manager {

    public static void registerNewUser(Role role, String username, String password) {
        switch (role) {
            case CLERK:
                Clerk newClerk = new Clerk(username, password);
                newClerk.saveInfo();
                break;
            case DOCTOR:
                Doctor newDoctor = new Doctor(username, password);
                newDoctor.saveInfo();
                break;
        }
    }

}
