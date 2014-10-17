package gwfragmenttutorial.bitxbit.org.fragmenttutorial.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import gwfragmenttutorial.bitxbit.org.fragmenttutorial.R;
import gwfragmenttutorial.bitxbit.org.fragmenttutorial.model.Car;
import gwfragmenttutorial.bitxbit.org.fragmenttutorial.model.CarParser;
import gwfragmenttutorial.bitxbit.org.fragmenttutorial.model.ListingAdapter;

public class FragmentListing extends Fragment {
    private ListView listing;

    public FragmentListing() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listing, container, false);
        listing = (ListView) rootView.findViewById(R.id.list_listing);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        List<Car> cars = new CarParser(getActivity().getApplicationContext()).parse();
        listing.setAdapter(new ListingAdapter(getActivity().getApplicationContext(), cars));
    }
}
