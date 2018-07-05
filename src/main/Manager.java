package main;

import clinic.members.Clerk;
import clinic.members.Doctor;
import clinic.members.Role;
import clinic.members.User;
import exceptions.loginExceptions.DuplicateUsernameException;
import exceptions.loginExceptions.IncorrectPaswordException;
import exceptions.loginExceptions.UserNotFoundException;

public abstract class Manager {

    public static void registerNewUser(Role role, String username, String password) throws DuplicateUsernameException {
        boolean duplicate = false;

        //Check for duplicate password
        for (int i = 0; !duplicate && i < User.getUsers().size(); i++) {
            if (User.getUsers().get(i).getUsername().equals(username)) {
                duplicate = true;
            }
        }

        if (duplicate) {
            throw new DuplicateUsernameException("Sorry! Duplicate password!\n");
        } else {
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

    public static User login(String username, String password) throws UserNotFoundException, IncorrectPaswordException {
        return User.login(username, password);
    }

}
