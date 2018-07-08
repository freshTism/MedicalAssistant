package utility;

public abstract class Utility {

    public static boolean isParametersNull(String ... parameters) {
        boolean isNull = false;

        for (String parameter : parameters) {
            if (parameter == null) {
                isNull = true;
                break;
            }
        }

        return isNull;
    }

}
