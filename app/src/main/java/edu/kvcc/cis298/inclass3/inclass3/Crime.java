package edu.kvcc.cis298.inclass3.inclass3;

import java.util.UUID;

/**
 * Created by jmartin5229 on 10/19/2015.
 */
public class Crime {

    //Private variables for our models
    private UUID mId;
    private String mTitle;

    //Default constructor
    public Crime () {
        //Make a new unique id for this particular crime
        mId = UUID.randomUUID();
    }


    // Getters and setters
    // only need
    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
