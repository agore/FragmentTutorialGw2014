package gwfragmenttutorial.bitxbit.org.fragmenttutorial.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import gwfragmenttutorial.bitxbit.org.fragmenttutorial.R;
import gwfragmenttutorial.bitxbit.org.fragmenttutorial.model.Car;


public class ActivityMain extends Activity implements FragmentListing.OnCarSelectedListener, FragmentListing.OnFirstCarIdentifiedListener {
    private final static String FRAGMENT_LISTING_TAG = "fragment_listing_tag";
    private final static String FRAGMENT_DETAILS_TAG = "fragment_details_tag";
    private boolean isDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // get instance of fragmentManager
            FragmentManager fragmentManager = getFragmentManager();
            // this will perform transactions on fragments
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // create new instance of fragment with all the listings
            Fragment fragmentListing = new FragmentListing();
            // add this fragment to it's placeholder in layout, fragment will be tagged with FRAGMENT_LISTING_TAG
            fragmentTransaction.add(R.id.generic_fragment_placeholder, fragmentListing, FRAGMENT_LISTING_TAG);
            // commit transaction for the changes to take effect
            fragmentTransaction.commit();

            // now we have to determine whether the current device supports dual pane mode or not
            // in order to do this, we'll check whether fragment_details view is present in activity layout
            View fragmentDetailsFrame = findViewById(R.id.fragment_details);
            if(fragmentDetailsFrame != null && fragmentDetailsFrame.getVisibility() == View.VISIBLE) {
                isDualPane = true;
            } else {
                isDualPane = false;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCarSelected(Car car) {
        // This callback is invoked when the user clicks on any of the car listings
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if(isDualPane) {
            // replace fragment in the details pane on the right side of the screen
            fragmentTransaction.replace(R.id.fragment_details, FragmentDetails.newInstance(car));
        } else {
            // replace the current fragment in the generic fragment placeholder
            // make sure to add this fragment to the back stack to enable back button functionality
            fragmentTransaction.replace(R.id.generic_fragment_placeholder, FragmentDetails.newInstance(car)).addToBackStack(FRAGMENT_LISTING_TAG);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onFirstCarIdentified(Car car) {
        if(isDualPane) {
            // if this is a dual pane, we have to display details for the first car in the list
            getFragmentManager().beginTransaction().replace(R.id.fragment_details, FragmentDetails.newInstance(car)).commit();
        } else {
            // else, there is nothing to do, so no op
        }
    }
}
