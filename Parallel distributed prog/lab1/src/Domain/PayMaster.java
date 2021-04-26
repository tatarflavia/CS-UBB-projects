package Domain;

import Repository.ProductRepository;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PayMaster extends ProductRepository implements Runnable {
    //collects bills and has its own product inventory(products that were sold by it)
    //it's a thread

    private int id; //thread id
    private ProductRepository storeProductRepository; //whole store
    private Lock _lockMechanism=new ReentrantLock(); //mutex

    public PayMaster(int id, ProductRepository storeProductRepository) {
        this.id = id;
        this.storeProductRepository = storeProductRepository;
    }

    @Override
    public void addProduct(Product product, int quantity){
        super.addProduct(product,quantity);
    }



    //here we remove the quantities sold by this cashier in all the sale operations from the store inventory
    @Override
    public void run() {
        System.out.println("Thread "+id+" starts removing quantities from store....\n");
        for(Product product:this.getProducts()){
            _lockMechanism.lock();
            int quantityToRemove=this.getQuantityForProduct(product);
            try{
                storeProductRepository.removeQuantity(product,quantityToRemove);
                System.out.println("Thread "+id+" removed from product: "+product.getId()+" quantity:"+  quantityToRemove+"\n");
            }
            catch (Error e){
                System.out.println(e.getMessage());
            }

            _lockMechanism.unlock();
        }
    }

    public double getTotalAmountOfMoney(){
        _lockMechanism.lock();
        double price=super.getTotalAmountOfMoney();
        _lockMechanism.unlock();
        return price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("PayMasterThread" +
                id +"{");
        for (Product key : this.getProducts()) {
            string.append(key).append("=").append(this.getQuantityForProduct(key)).append(", \n");
        }
        string.append("}");
        return string.toString();
    }
}
