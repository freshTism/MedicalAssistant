package clinic.members;

public class Doctor extends User {

    public Doctor(String username, String password) {
        super(username, password);
        this.setRole(Role.DOCTOR);
    }

    @Override
    public String toString() {
        String result;
        result = "پزشک\n";
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
        users.add(new Doctor(username, password));
    }
}
