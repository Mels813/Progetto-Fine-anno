public class Persona{
    private Strng nome;
    private String email;
    private String telefono;
    private String pasword;

    public Persona(String nome, String email, String telefono, String pasword) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefono;
        this.pasword = pasword;
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
    public String getPasword() {
        return pasword;
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
    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    //metodi

    

    public String toString() {
        return "Nome: " + nome + ", Email: " + email + ", Telefono: " + telefono + ", Password: " + pasword;
    }
}