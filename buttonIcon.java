import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

//classe per aggiungere l'immagine su un bottone

public class buttonIcon extends JButton{
    buttonIcon(String imgPath, int width, int heigth){
        setIcon(new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(width, heigtj, Image.SCALE_SMOOTH)));

        setBackground(Color.white);
        setBorder(null);
        setFocusPainted(false);
    }
}
