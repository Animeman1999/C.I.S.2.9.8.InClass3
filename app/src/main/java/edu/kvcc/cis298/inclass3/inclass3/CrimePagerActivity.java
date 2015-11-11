package edu.kvcc.cis298.inclass3.inclass3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by jmartin5229 on 11/11/2015.
 */
public class CrimePagerActivity extends FragmentActivity {

    //Variable to get us access to the ViewPager Wide in the View
    private ViewPager mViewPager;

    //Variable to hold all of our crime records hat the ViewPager
    //will need to use.
    private List<Crime> mCrimes;

    //Setup a string const for the key part of the Extra
    private static final String EXTRA_CRIME_ID = "edu.kvcc.cis298.inclass3.crime_id";

    //This method is public and static so that ANY other activity or fragment
    //that might want to start this Activity can get a propperly formated
    //intent that will allow this activity to start successfully.
    public static Intent newInent (Context packageContext, UUID crimeID){
        // make new intent
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        //put the passed in crimeId as an extra using the key declared above
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        //return the intent
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the content of this Activity to the crime pager layout.
        setContentView(R.layout.activity_crime_pager);

        //Get the UUID from the Extras for the activity.
        //Since UUID is not a simple tpe such as int or double
        //it must be of a type that implements the serailizable
        //interface.  UUID does implement serializable, so it can
        //be sent through as an Extra using the serializable type
        //We didn't have to do anything with serializable.  We just
        //need to know that UUID is serailizable, and so that is
        //the method we used to stor and retrieve it.  Since it arrives
        //here as a serializable, it must be cast to a UUID.  That is
        //the (UUID) part

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        //Get the view from the layout.
        mViewPager = (ViewPager)findViewById(R.id.activity_crime_pager_view_pager);

        //Get a reference to our Crime collection. This is the singleton.
        //The get method reurns the single instance of the crime lab
        //that we want to use.
        mCrimes = CrimeLab.get(this).getmCrimes();
        //Create a new FragmentManger. The Adapter that we are going
        // to use to wire up our daa and he viewpager requires us
        //to supply a fragment manager. It need the manager so that
        //it can do the work of swapping out one CrimeFragment, and
        //loading a new one. We did this ourselves with the fragments
        //that we were loading, however the viewpager will do this for
        //us automatically.
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Set the adapter for the viewpager with a unnamed FragmentStatePagerAdapter.
        //There are 2 methods that we have to override to finish it out.
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            // Get a spicific item from the crimes collection
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                //Return a new instance of a crime fragment using the
                //static method on the CrimeFragment Class the we created.
                return CrimeFragment.newInstance(crime.getId());
            }

            //Get the count for the size of the crime collection
            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        //For each crime in the crimes list
        for (int i = 0; i < mCrimes.size(); i++){
            //Check to see i he id of the current crime matches he
            //one hat was sent in from the CrimeListActivity that started
            //ths activity. If so, that is he one that we want o show first.
            if (mCrimes.get(i).getId().equals(crimeId)){
                //Se the current item of the viewPager to the one that was
                //sent from the view.
                mViewPager.setCurrentItem(i);
                //Since we found our match, no need to keep looping.
                // Other was would be to set i = to mCrimes.size() so as not to use a break
                //for single entry single exit.
                break;
            }
        }
    }

}
