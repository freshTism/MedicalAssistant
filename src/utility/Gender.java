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

    public static Gender searchWithName(String name) {
        Gender result = null;

        for (Gender gender : Gender.values()) {
            if (gender.getGender().equals(name)) {
                result = gender;
                break;
            }
        }

        return result;
    }
}