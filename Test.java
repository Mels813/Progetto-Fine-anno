package org.example;

import java.util.ArrayList;

public class Test{
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/deliveryapp";
        String user = "root";
        String pswrd = "";
        int tmp, i;
        ArrayList<Prodotto> prodottos = new ArrayList<>();

        Restaurant r1 = new Restaurant("","","","1234",0,"","");
        Prodotto pd = new Prodotto("margherita",7,"pizze","pom mozz",r1);

        prodottos = r1.getProducts(url,user,pswrd);

        for(i=0;i<prodottos.size();i++){
            System.out.println(prodottos.get(i).toString());
        }

    }
}