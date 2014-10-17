package gwfragmenttutorial.bitxbit.org.fragmenttutorial.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gwfragmenttutorial.bitxbit.org.fragmenttutorial.R;

public class ListingAdapter extends BaseAdapter {
    private final Context context;
    private List<Car> cars;

    public ListingAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
    }

    @Override
    public int getCount() {
        return cars == null ? 0 : cars.size();
    }

    @Override
    public Object getItem(int i) {
        return cars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((Car) getItem(i)).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listing_cell, parent, false);
            ViewHolder vh = new ViewHolder();
            vh.name = (TextView) convertView.findViewById(R.id.txt_car_name);
            vh.img = (ImageView) convertView.findViewById(R.id.img_car_listing);
            convertView.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        Car c = (Car) getItem(position);
        vh.name.setText(c.getName());
        vh.img.setImageDrawable(context.getResources().getDrawable(context.getResources().getIdentifier(c.getImage(), "drawable", context.getPackageName())));
        return convertView;
    }

    private class ViewHolder {
        private TextView name;
        private ImageView img;
    }
}
