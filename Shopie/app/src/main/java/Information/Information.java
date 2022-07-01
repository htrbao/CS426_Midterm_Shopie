package Information;

import java.io.Serializable;
import java.util.List;

public class Information implements Serializable {
    private List<String> items;
    private String url;
    private String name;
    private String price;

    public List<String> getItems() {
        return items;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    private String brand;

    public Information(List<String> items, String url, String name, String price, String brand) {
        this.items = items;
        this.url = url;
        this.name = name;
        this.price = price;
        this.brand = brand;
    }
}
