package gwfragmenttutorial.bitxbit.org.fragmenttutorial.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import gwfragmenttutorial.bitxbit.org.fragmenttutorial.R;

public class CarParser {
    private Context context;

    public CarParser(Context context) {
        this.context = context;
    }

    public List<Car> parse() {
        List<Car> cars = new ArrayList<Car>();
        try {
            JSONArray arr = new JSONArray(getJson());
            int len = arr.length();
            for (int i = 0; i < len; i++) {
                JSONObject car = arr.getJSONObject(i);
                cars.add(new Car(car.getString("name"), car.getString("image"), car.getString("manufacturer"), car.getString("description")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cars;
    }

    private String getJson() {
        InputStream is = null;
        BufferedReader r = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = context.getResources().openRawResource(R.raw.cars);
            r = new BufferedReader(new InputStreamReader(is));
            String line = null;
            try {
                line = r.readLine();
                while (line != null) {
                    sb.append(line);
                    line = r.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            if (r != null) try {r.close();} catch(Exception e) {};
            if (is != null) try {is.close();} catch(Exception e) {};
        }

        return sb.toString();
    }
}
