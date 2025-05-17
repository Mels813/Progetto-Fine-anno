package org.example;

import java.util.ArrayList;

public class Consegna{
    private String rsName;
    private float cost;
    private ArrayList<Prodotto> cart;


    public Consegna(String rsName, float cost){
        this.rsName = rsName;
        this.cost = cost;
        this.cart = new ArrayList<>();
    }

    public String getRsName(){
        return rsName;
    }
    public float getCost(){
        return cost;
    }

    public void addProduct(Prodotto pd){
        cart.add(pd);
    }

    public Prodotto getProduct(int i){
        return cart.get(i);
    }
}

