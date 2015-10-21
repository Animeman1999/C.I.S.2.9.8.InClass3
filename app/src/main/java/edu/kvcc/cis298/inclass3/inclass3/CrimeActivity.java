package edu.kvcc.cis298.inclass3.inclass3;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);  // Inflates an ACTIVITY to create the view

        // Get a variable that represens the support version
        //of the fragment manager. If the Android OS version
        //of Fragment manager is imported, this statememt will
        //fail. Must have support version imported.
        FragmentManager fm = getSupportFragmentManager();  // again had to change the support library by deleting the wrong one.

        //Use the fragment manager to get the fragment that is currently
        //in the frame that we created in the crimeFragment.xml file.
        //On the start of the app, this will be null sinice there
        //won't be anything there until we add it. Once something is
        //Added, this method will return whatever is in it.
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //Check to see if the fragment is null. On starting the app, it
        //will be But once we add a gragment to the frame, it willnot be.
        if (fragment == null){
            // Since it is empty. lets create a new inistance of the
            //CrimeFragment.
            fragment = new CrimeFragment();
            //Now go through the work of adding it to the frame.
            //This is done by starting a transaction, then adding the
            //frament we would like to attach and then commitiing the
            //changes.
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)   // these are added to the fm method
                    .commit();

        }
    }

}
