package gwfragmenttutorial.bitxbit.org.fragmenttutorial.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gwfragmenttutorial.bitxbit.org.fragmenttutorial.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentDetails extends Fragment {

    public FragmentDetails() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listing, container, false);
        return rootView;
    }
}
