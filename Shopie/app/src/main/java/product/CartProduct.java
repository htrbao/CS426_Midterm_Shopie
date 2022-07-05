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

    public Integer getPriceInInt() {
        Integer total = 0;
        System.out.println(Price.length());
        for (int i = 0; i < Price.length() - 1; i++) {
            if((int)'0' <= (int)Price.charAt(i) && (int)Price.charAt(i) <= (int)'9') {
                total = total * 10 + (int)Price.charAt(i) - (int)'0';
                //System.out.println(total);
            }
        }

        return total;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public void incQuantity() {
        Quantity = String.valueOf(Integer.valueOf(Quantity) + 1);
    }

    public void descQuantity() {
        Quantity = String.valueOf(Integer.valueOf(Quantity) - 1);
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
