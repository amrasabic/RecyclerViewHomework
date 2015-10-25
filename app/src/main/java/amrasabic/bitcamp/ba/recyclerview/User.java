package amrasabic.bitcamp.ba.recyclerview;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class User implements Serializable {

    private UUID mUUID;
    private String mName;
    private String mSurname;
    private Date mDate;

    public User(String name, String surname) {
        mUUID = UUID.randomUUID();
        mName = name;
        mSurname = surname;
        mDate = new	Date();
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }


}
