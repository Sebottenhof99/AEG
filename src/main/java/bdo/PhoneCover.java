package bdo;

public class PhoneCover {

    public PhoneCover(String phoneName, String motive) {
        this.phoneName = phoneName;
        this.motive = motive;
    }

    private String phoneName;
    private String motive;

    public String getPhoneName() {
        return phoneName;
    }

    public String getMotive() {
        return motive;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }
}
