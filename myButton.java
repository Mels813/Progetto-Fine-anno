import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class myButton extends JButton{
    private Image img;

    myButton(String description, String pathName){
        try{
            this.img = ImageIO.read(new File(pathName));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(50,50,Image.SCALE_SMOOTH));

            //settaggio valori grafici
            setIcon(icon);
            setBackground(Color.white);
            setForeground(Color.black);
            setSize(new Dimension(100,50));
        }
        catch(IOException e){
            System.out.println(e);
        }

        setText(description);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setVerticalTextPosition(SwingConstants.CENTER);
    }
}