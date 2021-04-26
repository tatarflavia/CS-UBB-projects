package Domain;

import java.util.ArrayList;
import java.util.Map;

public class Bill {

    private Map<Product,Integer> listOfProducts;
    private float totalPrice;

    public Bill(Map<Product, Integer> listOfProducts) {
        this.listOfProducts = listOfProducts;
        this.totalPrice = calculatePrice();
    }

    public Map<Product, Integer> getListOfProducts() {
        return listOfProducts;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    private float calculatePrice(){
        float sum=0;
        for (Map.Entry<Product, Integer> entry : listOfProducts.entrySet()) {
            Product product = entry.getKey();
            sum+=product.getPrice();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "listOfProducts=" + listOfProducts +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
