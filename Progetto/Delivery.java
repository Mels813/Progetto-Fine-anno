/*
 * rappresentazione di una delivery, con cliente, ristorate e rider coinvolti
 */

import java.util.ArrayList;

public class Delivery{
    private Cliente restaurant;
    private Cliente client;
    private Rider rider;
    //lista di prodotti per la consegna
    ArrayList<Prodotto>cart;

    Delivery(Cliente client){
        cart = new ArrayList<>();
        this.client = client;
        this.rider = null;
        this.restaurant = null;

    }

    public boolean addProduct(Prodotto prodotto){
        //controllo se primo prodotto, in caso aggiunta ristorante
        if(cart.size()==0){
            addRestaurant(prodotto);
            cart.add(prodotto);
            cart.get(0).setQuantity(1);
            return true;
        }
        //controllo se lo stesso ristorate, non aggiungibili, piu' prodotti di ristorante diversi nella stessa consegna
        if(restaurant.getAddress().equals(prodotto.getRestaurant().getAddress())){
            //controllo se prodotto gia' presente nel carrello
            for(int i=0;i<cart.size();i++){
                if(cart.get(i).getNomeProd().equals(prodotto.getNomeProd())){
                    cart.get(i).setQuantity(cart.get(i).getQuantity()+1);
                    return true;
                }
            }
            cart.add(prodotto);
            cart.get(cart.size()-1).setQuantity(1);
            return true;
        }
        return false;
    }

    public int getProducts(){
        return cart.size();
    }

    public String getRestaurantName(){
        return restaurant.getName();
    }

    private float sumCart(){
        int i;
        float sum = 0f;

        for(i=0;i<cart.size();i++){
            sum += cart.get(i).getPrezzo()*cart.get(i).getQuantity();
        }
        return sum;
    }

    private void addRestaurant(Prodotto product){
        //in base al primo elemento messo nel carrello si capisce il ristorante da cui parte la consegna
        restaurant = product.getRestaurant();
    }

    //ricerca di tutti i rider disponibili ed assegnazione consegna al primo disponibile
    public void findRider(){
        /*
         * chiamata al database, controllo rider
         */
    }

    public void printDatas(){
        for(int i=0;i<cart.size();i++){
            System.out.println(cart.get(i).getNomeProd()+" quantita': "+cart.get(i).getQuantity());
        }
        System.out.println("conto totale: "+sumCart());
    }
}
