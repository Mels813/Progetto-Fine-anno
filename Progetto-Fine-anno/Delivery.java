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
        if(cart.size()==0){
            //aggiunta ristorante
            prodotto.setQuantity(1);
            cart.add(prodotto);
            restaurant = prodotto.getRestaurant();
            return true;
        }
        else{
            if(prodotto.getRestaurant().getAddress().equals(restaurant.getAddress())){
                for(int i=0;i<cart.size();i++){
                    //se il prodotto e' gia' presente nel carrello
                    if(cart.get(i).getNomeProd().equals(prodotto.getNomeProd())){
                        cart.get(i).setQuantity(cart.get(i).getQuantity()+1);
                        return true;
                    }
                }
                prodotto.setQuantity(1);
                cart.add(prodotto);
                return true;   //prodotto aggiunto
            }
            return false;   //elemento da aggiungere presente in un altro ristorante
        }
    }

    public boolean checkMoney(){
        float total = sumCart();

        if(total > client.getMoney()){
            return false;
        }
        client.setMoney(client.getMoney()-total);
        return true;
    }

    public int getProducts(){
        return cart.size();
    }

    public void removeProduct(Prodotto prodotto){
        //ciclo per trovare il prodotto da rimuovere
        for(int i=0;i<cart.size();i++){
            if(cart.get(i).getNomeProd().equals(prodotto.getNomeProd())){
                cart.remove(i);
                break;
            }
        }
    }

    public String getRestaurantName(){
        return restaurant.getName();
    }

    //conto totale
    private float sumCart(){
        int i;
        float sum = 0f;

        for(i=0;i<cart.size();i++){
            sum += cart.get(i).getPrezzo()*cart.get(i).getQuantity();
        }
        return sum;
    }

    //ricerca di tutti i rider disponibili ed assegnazione consegna al primo disponibile
    public void findRider(){
        /*
         * chiamata al database, scelta tra uno dei rider disponibili
         */
    }

    public void printDatas(){
        for(int i=0;i<cart.size();i++){
            System.out.println(cart.get(i).getNomeProd()+" quantita': "+cart.get(i).getQuantity());
        }
        System.out.println("conto totale: "+sumCart());
    }

    //suddivisione soldi tra rider e ristorante
    public void paySerive(){
        float total = sumCart();

        //20% commissione al rider
        rider.setSaldo(rider.getSaldo()+total*0.2f);
        restaurant.setMoney(restaurant.getMoney()+total*0.8f);
    }
}
