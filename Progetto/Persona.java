public class Persona{
    private String nome;
    private String email;
    private String telefono;
    private String password;

    public Persona(String nome, String email, String telefono, String password){
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
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

    //metodi

    

    public String toString() {
        return "Nome: " + nome + ", Email: " + email + ", Telefono: " + telefono + ", Password: " + password;
    }
}