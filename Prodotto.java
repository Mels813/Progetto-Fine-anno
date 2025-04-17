package org.example;

public class Prodotto{
    private String nomeProd;
    private float prezzo;
    private String categoria;
    private String descrizione;
    private int quantity; //quantita' di prodotti nel carrello
    private Restaurant restaurant;

    public Prodotto(String nomeProd, float prezzo, String categoria,String descrizione, Restaurant restaurant){
        this.nomeProd = nomeProd;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.quantity = 0;
        this.restaurant = restaurant;
    }

    //getter
    public String getNomeProd(){
        return nomeProd;
    }
    public float getPrezzo(){
        return prezzo;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getDescrizione(){
        return descrizione;
    }
    int getQuantity(){
        return quantity;
    }
    Restaurant getRestaurant(){
        return restaurant;
    }

    //setter
    public void setNomeProd(String nomeProd){
        this.nomeProd = nomeProd;
    }
    public void setPrezzo(float prezzo){
        this.prezzo = prezzo;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String toString(){
        String str = "nome: "+nomeProd+" prezzo: "+prezzo+" categoria: "+categoria+" descrizione: "+descrizione;
        return str;
    }
}
