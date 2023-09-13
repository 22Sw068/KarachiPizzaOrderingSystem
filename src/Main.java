import java.util.ArrayList;

class Pizza {
    private String flavour;
    private short price;

    public Pizza(String flavour, short price) {
        this.flavour = flavour;
        this.price = price;
    }

    public String getFlavour() {
        return flavour;
    }

    public short getPrice() {
        return price;
    }

    public String toString() {
        return "Pizza Flavour : " + getFlavour()+ "     Price Of Pizza:" + getPrice();
    }
}

class PizzaShop {
    private String shopName;
    private ArrayList<Pizza> flavoursOffered;
    private int phoneNo;

    public PizzaShop(String shopName, int phoneNo) {
        this.shopName = shopName;
        this.flavoursOffered = new ArrayList<>();
        this.phoneNo = phoneNo;
    }
    public String getShopName(){
        return shopName;
    }
    public int getPhoneNo(){
        return phoneNo;
    }
    public ArrayList<Pizza> getFlavoursOffered() {
        return flavoursOffered;
    }


    public void displayFlavoursWithPrice() {
        System.out.println(" Shop Name:"+ getShopName()+"\t  Shop Phone Number :"+ getPhoneNo());
        for (Pizza pizza : flavoursOffered) {
            System.out.println(pizza);
        }
    }

    public short calculateAmount(String[] pizzas) {
        short totalAmount = 0;
        for (String pizza : pizzas) {
            for (Pizza piza : flavoursOffered) {
                if (pizza.equals(piza.getFlavour())) {
                    totalAmount += piza.getPrice();
                }
            }
        }
        return totalAmount;
    }
}

class PizzaOrders {
    PizzaShop orderFrom;
    private String[] pizzaSelected;
    private short totalAmount = 0;
    private float deliveryTime=25.0f;

    public PizzaOrders(PizzaShop orderFrom, String[] pizzaSelected) {
        this.orderFrom = orderFrom;
        this.pizzaSelected = pizzaSelected;
    }

    public short getTotalAmount() {
        return totalAmount;
    }

    public float getDeliveryTime() {
        return deliveryTime;
    }

    public void displayFlavours() {
        PizzaShop shop = orderFrom;
        shop.displayFlavoursWithPrice();
    }

    public void makeOrder(String... pizzaFlavours) {
        if (pizzaSelected == null) {
            pizzaSelected = new String[pizzaFlavours.length];
        }

        int i = 0;
        for (String pizzaFlavour : pizzaFlavours) {
            pizzaSelected[i++] = pizzaFlavour;
        }
    }
    public void calculateTotalAmount() {
        short amount = 0;
        for (String pizzaFlavour : pizzaSelected) {
            for (Pizza pizza : orderFrom.getFlavoursOffered()) {
                String pizzaNumber = pizza.getFlavour();
                if (pizzaFlavour.contains(pizzaNumber)) {
                    amount += pizza.getPrice();
                }
            }
        }
        totalAmount = amount;
    }
    public void printOrders() {
        System.out.println("Your Order Is: ");
        for (String pizzaflavour : pizzaSelected) {
            System.out.print( "\t \t"+pizzaflavour+" \t \t");
        }
        System.out.println("\nTotal amount: " + getTotalAmount());
        System.out.println(" Delivery time: " + getDeliveryTime()+ " Minutes ");
    }
}

public class Main {
    public static void main(String[] args) {
        Pizza pizza1 = new Pizza("i:Pepperoni Pizza",  (short) 1150);
        Pizza pizza2 = new Pizza("ii:Hawaiian Pizza",   (short) 1250);
        Pizza pizza3 = new Pizza("iii:Margherita Pizza", (short) 1360);
        Pizza pizza4 = new Pizza("iv:Capricciosa Pizza",(short) 1460);

        ArrayList<Pizza> flavours = new ArrayList<>();
        flavours.add(pizza1);
        flavours.add(pizza2);
        flavours.add(pizza3);
        flavours.add(pizza4);

        PizzaShop pizzaShop1 = new PizzaShop("POMO Neapolitan Pizzeria Shop", 123456789);
        PizzaShop pizzaShop2 = new PizzaShop("Domino's Pizza", 987654321);
        PizzaOrders pizzaOrder1 = new PizzaOrders(pizzaShop1, new String[]{"i:Pepperoni Pizza", "iii:Margherita Pizza"});
        PizzaOrders pizzaOrder2 = new PizzaOrders(pizzaShop2, new String[]{"ii:Hawaiian Pizza", "iv:Capricciosa Pizza "});

        pizzaShop1.getFlavoursOffered().addAll(flavours);
        pizzaShop2.getFlavoursOffered().addAll(flavours);
        System.out.println("--------------- Welcome to KARACHI Pizza shop-------------------");
        System.out.println("       -------------Pizza Shop 1:-------------");
        pizzaOrder1.displayFlavours();
        System.out.println("        -------------Pizza Shop 2:-------------");
        pizzaOrder2.displayFlavours();

        pizzaOrder1.makeOrder(" i:Pepperoni Pizza", "iii:Margherita Pizza");
        pizzaOrder1.calculateTotalAmount();
        System.out.println("-----------From Shop 1:-------- ");
        pizzaOrder1.printOrders();
        System.out.println(" You Have To Pay :"+ (pizza1.getPrice()+pizza3.getPrice())+" RS For Selected Orders");
        pizzaOrder2.makeOrder("ii:Hawaiian Pizza", "iv:Capricciosa Pizza ");
        pizzaOrder2.calculateTotalAmount();
        System.out.println(" -----------From Shop 2:--------- ");
        pizzaOrder2.printOrders();
        System.out.println(" You Have To Pay :"+ (pizza2.getPrice()+pizza4.getPrice())+" RS  For Selected Orders ");
    }
}
