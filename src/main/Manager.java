package main;

import clinic.members.Clerk;
import clinic.members.Doctor;
import clinic.members.Role;

public abstract class Manager {

    public static void registerNewUser(Role role, String username, String password) {
        switch (role) {
            case CLERK:
                new Clerk(username, password);
                break;
            case DOCTOR:
                new Doctor(username, password);
                break;
        }
    }

}
