public class Test{
    public static void main(String[] args) {
        Cliente cliente = new Cliente(null, null, null, null, 0, null, null);
        Cliente pizzeria = new Cliente(null, null, null, null, 0,"via giovanni","legnano");
        Prodotto margherita = new Prodotto("margherita", 7, "pizze", "pom mozz",pizzeria);
        Cliente sushi = new Cliente("sushi", null, null, null, 0, "via andrea","legnano");
        Prodotto sashimi = new Prodotto("sashimi", 10,"sushi", "salmone", sushi);

        Delivery dv = new Delivery(cliente);

       dv.addProduct(sashimi);
       dv.addProduct(margherita);

       System.out.println(dv.getProducts());
    }
}
