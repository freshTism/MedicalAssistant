package clinic;

public enum Insurance {

    DANA("دانا"),
    ALBORZ("البرز"),
    PARSIAN("پارسیان"),
    SINA("سینا"),
    RAZI("رازی"),
    DEY("دی"),
    MELLAT("ملت"),
    OMID("امید"),
    PASARGAD("پاسارگاد"),
    BAHMAN("بهمن"),
    MA("ما"),
    SARMAD("سرمد"),
    IRAN("ایران"),
    ASIA("آسیا"),
    MOALLEM("معلم"),
    KARAFARIN("کارآفرین"),
    SAMAN("سامان"),
    NOVIN("نوین"),
    MIHAN("میهن"),
    HAFEZ("حافظ"),
    KOSAR("کوثر"),
    TAAVON("تعاون"),
    ARMAN("آرمان"),
    AZAD("آزاد");

    private String insuranceName;

    Insurance(String name) {
        insuranceName = name;
    }

    public String getInsuranceName() {
        return insuranceName;
    }
}