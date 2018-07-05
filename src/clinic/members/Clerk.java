package clinic.members;

public class Clerk extends User {

    public Clerk(String username, String password) {
        super(username, password);
        this.setRole(Role.CLERK);
    }

    @Override
    public String toString() {
        String result;
        result = "منشی\n";
        result += username + "\n";
        result += password + "\n";
        return result;
    }

    private void writeInfoToFile() {
        //Reference the text file
        createInfoFile("infoList.txt", fwExMessage);
        outFile.println("منشی");
        outFile.println(username);
        outFile.println(password);
        outFile.println(" ");
        outFile.close();
    }

    @Override
    public void saveInfo() {
        writeInfoToFile();
        addToList();
    }

    @Override
    public void addToList() {
        users.add(new Clerk(username, password));
    }
}
