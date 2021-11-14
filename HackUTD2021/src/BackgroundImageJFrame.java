import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImageJFrame extends JPanel  {
	BufferedImage image; 

    JButton b1;
    JLabel l1;


    public BackgroundImageJFrame() throws IOException
    {

        setSize(400,400);

       //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        image = ImageIO.read(new File("C:/Users/cloyd/eclipse-workspace/HackUTD2021/src/background.png"));

        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon(image));
        add(background);


    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
    
    public static void main(String args[]) throws IOException {
    	new BackgroundImageJFrame();
    	System.out.println("yeehae");
    }
    
}
