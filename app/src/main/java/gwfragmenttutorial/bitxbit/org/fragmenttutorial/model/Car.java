package gwfragmenttutorial.bitxbit.org.fragmenttutorial.model;

import java.io.Serializable;

public class Car implements Serializable{
    private transient static int idGen = 0;
    private int id;
    private String name;
    private String image;
    private String manufacturer;
    private String description;

    public Car(String name, String image, String manufacturer, String description) {
        this.id = idGen++;
        this.name = name;
        this.image = image;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
