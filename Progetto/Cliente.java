/*
 * Classe per rappresentare clienti e ristoranti
 */

public class Cliente extends User{
   private String street;
   private String address;

    Cliente(String name, String email, String pswrd, String phone, float saldo, String address, String city){
        super(name,email,phone,pswrd,saldo);
        this.address = address;
    }

    String getStreet(){
        return street;
    }

    String getName(){
        return super.getNome();
    }

    String getMail(){
        return super.getEmail();
    }

    
    String getAddress(){
        return address;
    }
}
