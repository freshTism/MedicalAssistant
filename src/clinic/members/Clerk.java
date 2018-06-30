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
        outFile.println(toString());
        //کی باید فایلو ببندم؟
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
