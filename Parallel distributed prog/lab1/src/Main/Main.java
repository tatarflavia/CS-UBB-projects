package Main;

import Domain.Money;
import Domain.PayMaster;
import Domain.Product;
import Repository.ProductRepository;

import java.util.ArrayList;

public class Main
{
    private static Money totalInitialValueForInventory=new Money();
    private static ProductRepository productInventory; //inventory of whole store, to be given as reference to each thread/ cashier
    private static final int numberOfThreads=5;
    private static final int inventoryProductsNumber=800;
    private static ArrayList<PayMaster> payMasters=new ArrayList<>();
    private static ArrayList<Thread> threadList=new ArrayList<>();





    //init for sets of operations=sales aka paymasters objects
    private static void initialisePayMasters(){
        int id=1;
        System.out.println("Preparing lists of operations/cashiers for store...");
        for(int i=0;i<numberOfThreads;i++){
            PayMaster payMaster=new PayMaster(id,productInventory);
            int randomProductsNumber=getRandomInt(2,100); //how many operations=bill=sale=1 man for this one thread=cashier
            //System.out.println("products size: "+randomProductsNumber+"\n");
            for(int j=0;j<randomProductsNumber;j++){
                //adding products to be sold by this cashier in operations
                int randomProductID=getRandomInt(1,inventoryProductsNumber);
                int randomQuantity=getRandomInt(1,7);
                Product chosenProduct=productInventory.getProductByID(randomProductID);
                //System.out.println("product id: "+randomProductID+"\n");
                //System.out.println("quantity: "+randomQuantity+"\n");
                //System.out.println("product: "+chosenProduct+"\n");
                if(chosenProduct!=null){
                    payMaster.addProduct(chosenProduct,randomQuantity);
                }
            }
            System.out.println("Cashier"+payMaster.getId()+" is ready!\n");
            id++;
            payMasters.add(payMaster);
        }
    }

    private static void initialiseThreads(){
        for (PayMaster payMaster : payMasters) {
            threadList.add(new Thread(payMaster));
        }
    }

    private static void startThreads(){
        System.out.println("Starting "+threadList.size()+" threads...\n");
        for(Thread thread:threadList){
            thread.start();
        }
        System.out.println("Running threads...\n");
        for (Thread thread : threadList){
            try {
                int randomInt=getRandomInt(1,10);
                if(randomInt>5){
                    verifyMoneyTotal();
                }
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void verifyMoneyTotal(){
        //calculate total made by the cashiers aka threads
        double priceFromAllTransactions=0;
        for(PayMaster payMaster:payMasters){
            priceFromAllTransactions+=payMaster.getTotalAmountOfMoney();
        }
        double currentProductsValueFromInventory=Math.round(productInventory.getTotalAmountOfMoney() * 100.0) / 100.0;
        double difference=totalInitialValueForInventory.getAmount()-priceFromAllTransactions;
        double diff=Math.round(difference * 100.0) / 100.0;
        System.out.println("Substract from initial deposit "+Math.round(totalInitialValueForInventory.getAmount() * 100.0) / 100.0);
        System.out.println("The total price made by all cashiers/ operations "+Math.round(priceFromAllTransactions * 100.0) / 100.0);
        System.out.println("And compare it to current value of all products from store "+currentProductsValueFromInventory);
        System.out.println("=>compare: "+ (currentProductsValueFromInventory)+" and "+diff);
        if(currentProductsValueFromInventory==diff){
            System.out.println("The stock is ok!Verification passed.");
        }
        else{
            System.out.println("The stock is not ok! Verification failed.");
        }
    }



    private static String getAlphaRandomString(int n)
    {
        String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            // generate a random number between 0 to AlphaNumericString variable length
            int index= (int)(AlphaString.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaString.charAt(index));
        }
        return sb.toString();
    }

    public static double getRandomDouble(int min, int max) {
        return ((Math.random() * (max - min)) + min);
    }

    private static int getRandomInt(int min, int max) {
        return (int)(((Math.random() * (max - min)) + min));
    }

    private static void initialiseInventory(){
        int id=1;
        productInventory=new ProductRepository();
        for(int i=0;i<inventoryProductsNumber;i++){
            productInventory.addProduct(new Product(getRandomDouble(5,10),getAlphaRandomString(10),id),getRandomInt(1000,1500));
            id++;
        }

    }


    public static void main(String[] args) {
    {
        long startTime = System.nanoTime();
        System.out.println("The number of threads is: "+numberOfThreads);
        initialiseInventory();
        System.out.println("The number of products added to store inventory is: "+inventoryProductsNumber);
        totalInitialValueForInventory=new Money();
        totalInitialValueForInventory.setAmount(productInventory.getTotalAmountOfMoney());
        //System.out.println(productInventory);
        System.out.println("All products from inventory have an initial value of: "+totalInitialValueForInventory);
        initialisePayMasters();
        initialiseThreads();
        startThreads();
        System.out.println("Threads are done, stock verification starts now..");
        verifyMoneyTotal();
        //System.out.println("inventory now:"+productInventory);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  // milliseconds.
        System.out.println("The time passed is: "+duration +" milliseconds");
    }
}}


