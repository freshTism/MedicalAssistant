package utility;

public enum Gender {

    MALE("مرد"),
    FEMALE("زن");

    private String gender;

    Gender(String _gender) {
        gender = _gender;
    }

    public String getGender() {
        return gender;
    }
}