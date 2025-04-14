import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ResturantGUI extends JFrame{
    private JPanel panel;
    private ArrayList<Prodotto> prodotti;

    //costruttore
    public ResturantGUI (ArrayList<Prodotto> listaprodotti){
        setTitle("Ristorante");
        panel.setLayout(new BorderLayout());

        prodotti = listaprodotti;
        mostraProdotti();

        setContentPane(panel); //imposto il pannello come default (pannel principale)
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void mostraProdotti(){
        JPanel listaPanel = new JPanel();

        for(Prodotto p : prodotti){
            JPanel prodottoPanel = new JPanel(new FlowLayout());
            JLabel nome = new JLabel(p.getNomeProd());
            JButton dettagli = new JButton("Dettagli");

            dettagli.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    mostraDettagli(p);
                }
            });

            prodottoPanel.add(nome);
            prodottoPanel.add(dettagli);
            listaPanel.add(prodottoPanel);

            panel.add(listaPanel, BorderLayout.CENTER);
        }

        //necessari per quando cambia la schermata e aggiornarla 
        panel.removeAll();
        panel.add(listaPanel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    void mostraDettagli(Prodotto p){ // dettagli del prodotto(nome, prezzo, categoria, descrizione, quantita)
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

        //necessari per quando cambia la schermata e aggiornarla 
        panel.removeAll();
        panel.add(dettagliPanel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Prodotto> lista = new ArrayList<>();
            Cliente c = new Cliente("Ristorante XYZ", "igor.melis@icloud.com", "Igorinho", "3517568012", 3566, "Via della liberta 12", "Rescadlina");
            lista.add(new Prodotto("Pizza", 8.5f, "Piatto principale", "Pomodoro, mozzarella, basilico", c));
            lista.add(new Prodotto("Tiramisù", 4.0f, "Dolce", "Mascarpone, caffè, savoiardi", c));
            new ResturantGUI(lista);
        });
    }
}