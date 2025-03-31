public class Cliente extends Persona{
   private String street;
   private float saldo;
   private String city;
   private int n;

    Cliente(String name, String email, String pswrd, String phone, String street, int n, String city){
        super(name,email,phone,pswrd);
        this.street = street;
        this.saldo = 0f;
        this.city = city;
        this.n = n;
    }

    String getStreet(){
        return street;
    }

    int getN(){
        return n;
    }

    String getName(){
        return super.getNome();
    }

    String getMail(){
        return super.getEmail();
    }
    float getSaldo(){
        return saldo;
    }
    void setSaldo(float saldo){
        this.saldo = saldo;
    }
    String getCity(){
        return city;
    }

    @Override
    public String toString(){
        return super.toString()+" indirizzo: "+street+" "+n+" citta': "+city;
    }
}
