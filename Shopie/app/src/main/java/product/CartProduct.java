package product;

import java.io.Serializable;
import java.util.List;

public class CartProduct implements Serializable {
    private String Name;
    private String Price;
    private String UrlProduct;
    private String Type;

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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    private String Quantity;

    public CartProduct(){}
    public CartProduct(String name, String price, String urlProduct, String type, String quantity) {
        Name = name;
        Price = price;
        UrlProduct = urlProduct;
        Type = type;
        Quantity = quantity;
    }
}
