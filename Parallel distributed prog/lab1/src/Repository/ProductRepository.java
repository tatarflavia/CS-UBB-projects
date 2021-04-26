package Repository;

import Domain.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ProductRepository {

    private HashMap<Product,Integer> productRepository;

    public ProductRepository() {
        this.productRepository = new HashMap<>();
    }

    public void addProduct(Product product, int quantity){
        if(productRepository.containsKey(product)){
            productRepository.replace(product,productRepository.get(product)+quantity);
        }
        else{
            productRepository.put(product,quantity);
        }
    }

    public void removeQuantity(Product product, int quantity){
        if(productRepository.containsKey(product)){
            int oldQuantity=productRepository.get(product);
            if(oldQuantity<quantity){
                throw  new Error("can't extract that quantity because the inventory has less than that for this product!");
            }
            else{
                productRepository.replace(product,oldQuantity-quantity);
                if(productRepository.get(product)==0){
                    productRepository.remove(product);
                }
            }
        }
        else{
            throw new Error("can't remove what is not in the inventory in the first place!");
        }

    }


    public Product getProductByID(int id){
        for(Product product: this.getProducts()){
            if(id==product.getId())
                return product;
        }
        return null;
    }

    public Set<Product> getProducts(){
        return productRepository.keySet();
    }

    public int getQuantityForProduct(Product product){
        return productRepository.get(product);
    }

    public double getTotalAmountOfMoney(){
        double price=0;
        for(Product product: this.getProducts()){
            price+=product.getPrice()*this.getQuantityForProduct(product);
        }
        return price;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");
        for (Product key : productRepository.keySet()) {
            string.append(key).append("=").append(productRepository.get(key)).append(", \n");
        }
        string.append("}");
        return string.toString();
    }


}
