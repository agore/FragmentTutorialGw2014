package gwfragmenttutorial.bitxbit.org.fragmenttutorial.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import gwfragmenttutorial.bitxbit.org.fragmenttutorial.R;
import gwfragmenttutorial.bitxbit.org.fragmenttutorial.model.Car;
import gwfragmenttutorial.bitxbit.org.fragmenttutorial.model.CarParser;
import gwfragmenttutorial.bitxbit.org.fragmenttutorial.model.ListingAdapter;

public class FragmentListing extends Fragment {
    private ListView listing;
    private OnCarSelectedListener listener;
    private OnFirstCarIdentifiedListener onFirstCarIdentifiedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnCarSelectedListener) activity;
        onFirstCarIdentifiedListener = (OnFirstCarIdentifiedListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listing, container, false);
        listing = (ListView) rootView.findViewById(R.id.list_listing);

        listing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                listener.onCarSelected((Car) adapterView.getItemAtPosition(position));
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        List<Car> cars = new CarParser(getActivity().getApplicationContext()).parse();
        listing.setAdapter(new ListingAdapter(getActivity().getApplicationContext(), cars));
        Car firstCarInList = (Car) listing.getAdapter().getItem(0);
        onFirstCarIdentifiedListener.onFirstCarIdentified(firstCarInList);

    }

    public static interface OnCarSelectedListener {
        void onCarSelected(Car car);
    }

    public static interface OnFirstCarIdentifiedListener {
        void onFirstCarIdentified(Car car);
    }



}
