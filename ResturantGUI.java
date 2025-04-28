import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ResturantGUI extends JFrame{
    private JPanel panel;
    private ArrayList<Prodotto> prodotti;

    public ResturantGUI(ArrayList<Prodotto> listaprodotti){
        setTitle("Ristorante");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        prodotti = listaprodotti;
        mostraProdotti();

        setContentPane(panel);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void mostraProdotti(){
        JPanel listaPanel = new JPanel();
        listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS)); // Imposta il layout in verticale

        for(int i = 0; i < prodotti.size(); i++){
            JPanel prodottoPanel = new JPanel(new FlowLayout());
            JLabel nome = new JLabel(prodotti.get(i).getNomeProd());
            JButton dettagli = new JButton("Dettagli");

            int index = i; // Creiamo una variabile finale per evitare problemi con il riferimento

            dettagli.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    mostraDettagli(prodotti.get(index));
                }
            });

            prodottoPanel.add(nome);
            prodottoPanel.add(dettagli);
            listaPanel.add(prodottoPanel);
        }

        panel.removeAll();
        panel.add(listaPanel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    void mostraDettagli(Prodotto p){
        JPanel dettagliPanel = new JPanel(new BorderLayout());

        JTextArea txtdettagli = new JTextArea(
            "Nome: " + p.getNomeProd() +
            "\n Prezzo: " + p.getPrezzo() + "$" +
            "\n Categoria: " + p.getCategoria() +
            "\n Descrizione: " + p.getDescrizione() +
            "\n Quantita prodotto: " + p.getQuantity()
        );
        txtdettagli.setEditable(false);

        JButton btnAggiungi = new JButton("+");
        JButton btnRimuovi = new JButton("-");
        JButton btnIndietro = new JButton("indietro");

        btnAggiungi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                p.setQuantity(p.getQuantity()+1);
                mostraDettagli(p);
            }
        });

        btnRimuovi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(p.getQuantity() > 0){
                    p.setQuantity(p.getQuantity()-1);
                    mostraDettagli(p);
                }
            }
        });

        btnIndietro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mostraProdotti();
            }
        });

        JPanel panelBottoni = new JPanel();
        panelBottoni.add(btnAggiungi);
        panelBottoni.add(btnRimuovi);
        panelBottoni.add(btnIndietro);

        dettagliPanel.add(txtdettagli, BorderLayout.CENTER);
        dettagliPanel.add(panelBottoni, BorderLayout.SOUTH);

        panel.removeAll();
        panel.add(dettagliPanel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
    public static void main(String[] args) {
        ArrayList<Prodotto> listaProdotti = new ArrayList<>();
        listaProdotti.add(new Prodotto("Pizza Margherita", 10.0f, "Pizza", "Pizza con pomodoro e mozzarella", null));
        listaProdotti.add(new Prodotto("Pasta alla Carbonara", 12.5f, "Pasta", "Pasta con pancetta, uova e pecorino", null));
        listaProdotti.add(new Prodotto("Coca Cola", 2.5f, "Bevande", "Bibita gassata", null));

        new ResturantGUI(listaProdotti);
    }
}
