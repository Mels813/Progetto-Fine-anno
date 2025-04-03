/*
 * Classe per rappresentare un soggetto generale, utilizzatore dell'app
 */

public class User{
    private String nome;
    private String email;
    private String telefono;
    private String password;
    private float saldo;

    public User(String nome, String email, String telefono, String password, float saldo){
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.saldo = saldo;
    }

    //getter
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getPassword() {
        return password;
    }
    public float getSaldo(){
        return saldo;
    }

    //setter
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setPasword(String password) {
        this.password = password;
    }
    public void setSaldo(float saldo){
        this.saldo = saldo;
    }

    

    public String toString() {
        return "Nome: " + nome + ", Email: " + email + ", Telefono: " + telefono + ", Password: " + password;
    }
}