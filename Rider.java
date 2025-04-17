/*
 * Classe utente per le consegne
 */

package org.example;

public class Rider extends User{
    private boolean free;   //in viaggio oppure libero

    Rider(String name, String mail, String phone, String pswrd, float saldo){
        super(name,mail,phone,pswrd,saldo);
        this.free = true;
    }

    boolean getFree(){
        return free;
    }

    void setFree(boolean free){
        this.free = free;
    }

    public void goToDestination(String addressString){
        //integrazione google maps per raggiungere la destinazione
    }
}
