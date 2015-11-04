package edu.kvcc.cis298.inclass3.inclass3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by jmartin5229 on 11/2/2015.
 */
public class CrimeListFragment extends Fragment
{

    // Class level variable to hold he recycler view
    private RecyclerView mCrimeRecyclerView;

    //Variable to hold an instance of the adapter
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    // get the veiw from the layouts that will be displayed
    //Use the inflator to inflate the layout to java code
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        // Get a reference to the recycler view in the layout file
        //Remeber that we have to call findViewById on he view
        //that we created about. It is not an automatic method
        //like it was for an Activity
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);

        // The recycler view requires that it is given a Layout
        //Manager. The cecyclerview is failry new control, and
        //is not capable of displaying the list items on the screen
        //A LinearLayoutManager is required to do that work.  Therfore
        //we create a new LinearLayoutManager, and pass it the context
        //of which it needs to operate it.  The contesxt is passed by using
        //the getActivity method. Which gets th activitiy that is
        //hosting this fragment.
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Call the updateUI method to do the work of getting the
        //data from the CrimeLab, setting it up with the adaper
        //and then adding the adapter to the recycler view.
        updateUI();

        //Return the created view
        return view;
    }

    private  void   updateUI() {
        // Get the collectioni of data from the crimeLab
        //singleton. The get method constructor requires that
        // a context is passed in, so we send it the hosting
        //activity of this gragment.
        CrimeLab crimeLab = CrimeLab.get(getActivity());

        //Get the actual list of crime from the CrimeLab class
        List<Crime> crimes = crimeLab.getmCrimes();

        //Create a new crimeAdapter and send it over the list
        //of crimes. Crime adapter needs the list of crimes so
        //that it can work with the recyclerview o diplay them.
        mAdapter = new CrimeAdapter(crimes);

        //Take the adaper that we just created and set it as the
        //adaper that the recycler view is going to use.
        mCrimeRecyclerView.setAdapter(mAdapter);

    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Creae a class level variable o hold he view for
        //this holder
        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        //Constructor that takes in a View. The parent constructor
        //is called and hen the passed in View is assinged
        //to the class level version
        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            // Do assiment to class level var. Useing the findviewbyid method
            // to get access to the varoius controls we want to work with.
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);

            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);

            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime) {
            //Method to take in a instance of a crime, and assign it to the
            //class level version. Then use the class level to take
            //properties from the crime and assign them to the varous
            //view controls.
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        // This method must be implemented because we have this class
        //implementing the onClickListener interface. This method will
        //do the work toastin the title of the crime that was clicked on.
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        //Class level variable to hold 'data of our app.
        //This will be the list of crimes
        private List<Crime> mCrimes;


        //Constructor that takes ini a list of crimes, and
        //then assigns them to the class level var.
        public CrimeAdapter (List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // get a LayoutInflater
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            //Inflae the view that we would like to use to display a single
            //list item.
            //call the one we created
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            //Return a new CrimeHolder with the view passed in as a parameter.
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            //Get the crime that is a the index declared by he variable posiion
            //and assig it to a local crime variable
            Crime crime = mCrimes.get(position);

            //Send the crime over to bindCrime method we wrote on
            //the crimeholder class. That method does the work of setting
            //the properies of the crime to the layout controls in the
            //custom layout we made.
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            //Just return the size of he crime list
            return mCrimes.size();
        }
    }
}
