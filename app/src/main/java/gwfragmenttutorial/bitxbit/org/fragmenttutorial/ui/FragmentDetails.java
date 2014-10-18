package gwfragmenttutorial.bitxbit.org.fragmenttutorial.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gwfragmenttutorial.bitxbit.org.fragmenttutorial.R;
import gwfragmenttutorial.bitxbit.org.fragmenttutorial.model.Car;

public class FragmentDetails extends Fragment {

    private static final String CAR = "car";
    private TextView title;
    private TextView manufacturer;
    private TextView description;
    private ImageView image;

    public static FragmentDetails newInstance(Car car) {
        Bundle b = new Bundle();
        b.putSerializable(CAR, car);
        FragmentDetails fd = new FragmentDetails();
        fd.setArguments(b);
        return fd;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        title = (TextView) rootView.findViewById(R.id.txt_details_title);
        manufacturer = (TextView) rootView.findViewById(R.id.txt_details_manufacturer);
        description = (TextView) rootView.findViewById(R.id.txt_details_description);
        image = (ImageView) rootView.findViewById(R.id.img_details_pic);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Car c = (Car) getArguments().getSerializable(CAR);
        title.setText(c.getName());
        manufacturer.setText(c.getManufacturer());
        description.setText(c.getDescription());
        image.setImageDrawable(getResources().getDrawable(getIdForNamedDrawable(c.getImage())));
    }

    private int getIdForNamedDrawable(String drawable) {
        return getResources().getIdentifier(drawable, "drawable", getActivity().getPackageName());
    }
}
