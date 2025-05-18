package org.example;

import java.util.ArrayList;

public class Consegna{
    private String rsName;
    private float cost;
    private ArrayList<Prodotto> cart;
    private int id;


    public Consegna(String rsName, float cost, int id){
        this.rsName = rsName;
        this.cost = cost;
        this.cart = new ArrayList<>();
        this.id = id;
    }

    int getId(){
        return id;
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

    public int getLength(){
        return cart.size();
    }

    public Prodotto getProduct(int i){
        return cart.get(i);
    }
}

