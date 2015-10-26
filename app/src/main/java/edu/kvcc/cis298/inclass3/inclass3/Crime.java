package edu.kvcc.cis298.inclass3.inclass3;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jmartin5229 on 10/19/2015.
 */
public class Crime {

    //Private variables for our models
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;


    //Default constructor
    public Crime () {
        //Make a new unique id for this particular crime
        mId = UUID.randomUUID();
        mDate = new Date();
    }


    // Getters and setters
    // only need
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
