public class Prodotto {
    private String nomeProd;
    private float prezzo;
    private String categoria;
    private String descrizione;
    private String IDRistorante;

    public Prodotto(String nomeProd, float prezzo, String categoria, String IDRistorante, String descrizione) {
        this.nomeProd = nomeProd;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.IDRistorante = IDRistorante;
    }

    //getter
    public String getNomeProd() {
        return nomeProd;
    }
    public float getPrezzo() {
        return prezzo;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getIDRistorante() {
        return IDRistorante;
    }
    public String getDescrizione() {
        return descrizione;
    }
    //setter
    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }
    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setIDRistorante(String IDRistorante) {
        this.IDRistorante = IDRistorante;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    //metodi
    public String toString() {
        return "Nome Prodotto: " + nomeProd + ", Prezzo: " + prezzo + "Descrizione: " + descrizione + ", Categoria:" + categoria + ", ID Ristorante: " + IDRistorante;
    }

}
