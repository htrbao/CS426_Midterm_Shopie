package product;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    public Product(String brand, String category, String description, List<String> items, String name, String price, String urlProduct) {
        Brand = brand;
        Category = category;
        Description = description;
        Items = items;
        Name = name;
        Price = price;
        UrlProduct = urlProduct;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<String> getItems() {
        return Items;
    }

    public void setItems(List<String> items) {
        Items = items;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getUrlProduct() {
        return UrlProduct;
    }

    public void setUrlProduct(String urlProduct) {
        UrlProduct = urlProduct;
    }

    private String Brand;
    private String Category;
    private String Description;
    private List<String> Items;
    private String Name;
    private String Price;
    private String UrlProduct;

    public Product(){}
}
