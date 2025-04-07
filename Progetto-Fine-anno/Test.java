public class Test{
    public static void main(String[] args){
        Cliente cliente = new Cliente(null, null, null, null, 0,"via giovanni","legnano");
        Cliente ristorante1 = new Cliente("sushi", null, null, null, 0,"via rossi","legnano");
        Cliente ristorante2 = new Cliente("pizzeria", null, null, null, 0, "via gialli","legnano");

        Prodotto p1 = new Prodotto("sashimi", 12, null, null, ristorante1);
        Prodotto p2 = new Prodotto("pizza", 13, null, null, ristorante1);

        Delivery dv = new Delivery(cliente);

        dv.addProduct(p1);
        dv.addProduct(p2);
        dv.addProduct(p1);

        dv.printDatas();// 2
    }
}