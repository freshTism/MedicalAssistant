package main;

import clinic.members.Clerk;
import clinic.members.Doctor;
import clinic.members.Role;
import clinic.members.User;
import exceptions.loginExceptions.IncorrectPaswordException;
import exceptions.loginExceptions.UserNotFoundException;

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

    public static User login(String username, String password) throws UserNotFoundException, IncorrectPaswordException {
        return User.login(username, password);
    }

}
