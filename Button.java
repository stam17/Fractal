import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Button {

    static Random rand = new Random();
    JButton b;

    public Button(Main m) {

        //Sets a label and size/location of the "Randomize" button
        b=new JButton("Randomize!");
        b.setBounds(50,100,105,30);

        //Generates a random fractal tree with randomized features if the button is pressed
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ //inspired by: https://www.javatpoint.com/java-jbutton
                Main.TYPE = rand.nextInt(4) + 2;
                Main.ANGLE = rand.nextDouble() * Math.PI * 2;
                Main.GENERATION = rand.nextInt(10) + 1;
                Main.LENGTH = 0.4 + (rand.nextDouble() * 0.24);
            }
        });
        m.add(b);
    }
}

