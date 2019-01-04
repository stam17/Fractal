import java.awt.*;
import javax.swing.*;

public class Main extends JPanel{

    //Dimensions of the JPanel
    static final int BOX_WIDTH = 1024;
    public static final int BOX_HEIGHT = 768;

    //Fractal to be drawn
    private static Fractal2 fract;

    //Four features of the fractal
    public static int TYPE = 2;
    public static double ANGLE;//180 minus the angle between branch n and n+1
    public static int GENERATION;
    public static double LENGTH;//between 0.4-0.7 works well

    //Elements of the graphical user interface
    public static Slider angleSlider;
    public static Slider lengthSlider;
    public static Slider generationSlider;
    public static Slider branchSlider;
    public static Button button;

    private boolean startup = true;//if true do the startup animation
    private boolean partyMode = true;//makes the party mode sign blinking

    public static Font titleFont = new Font("Sans_Serif", Font.BOLD, 40);
    public static Font mainFont = new Font("Sans_Serif", Font.PLAIN, 12);
    //Font styles learned from oracle docs https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html

    class Runner implements Runnable{//Runner learned from previous lab: OOBouncing.java
        public void run(){
            while(true) {
                if (startup){startAnimation();}//run starting animation
                update();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    //Constructor- instantiates the fractal and starts the thread
    public Main(){

        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        fract = new Fractal2(TYPE, ANGLE, GENERATION, LENGTH);

        Thread mainThread = new Thread(new Runner());
        mainThread.start();

    }

    //Called in Fractal2 in case TYPE is not a number between 2 and 6
    public static void showUsageAndExit () {

        System.err.printf("USAGE: java Main <BranchNumber [2|3|4|Random]>\n" +
                "                 <Angle [integer between 0-180]\n"   +
                "                 <Number of Generations [integer between 1-10]>\n" +
                "                 <Length [double between 0.0-1.0]\n"
        );
        System.exit(1);

    }

    private void draw(Graphics2D g2d){
        g2d.setColor(Color.GRAY);

        //Adds a title (part of the start-up animation)
        if(startup) {
            g2d.setFont(titleFont);
            g2d.drawString("WELCOME TO FRACTAL GENERATOR", BOX_WIDTH/2-400, BOX_HEIGHT/2);
            g2d.setFont(mainFont);
        }

        //Adds text to the screen when the program enters "party mode" (i.e. number of branches = 6)
        if (TYPE == 6) {
            g2d.setFont(titleFont);
            g2d.setColor(Color.ORANGE);
            if (partyMode){
                g2d.drawString("PARTY MODE!", BOX_WIDTH/2-250, BOX_HEIGHT/2);
                partyMode = false;
            } else {partyMode = true;}
            g2d.setFont(mainFont);

        }

        //Draws text that appears on the screen regardless of condition

        //In the top left of the screen: shows changing values of the four features of the fractal
        g2d.drawString("LENGTH: 0." + Math.round(LENGTH*100) + " x Previous", 100, 20);
        g2d.drawString("ANGLE: " + (Math.round(Math.toDegrees(ANGLE))), 100, 40);
        g2d.drawString("GENERATIONS: " + GENERATION, 100, 60);
        g2d.drawString("NUMBER OF BRANCHES: " + TYPE, 100, 80);

        //Labels for the four sliders at the bottom of the screen
        g2d.drawString("Angle (0-180 degrees)", 70, BOX_HEIGHT-50);
        g2d.drawString("Branch length", 100, BOX_HEIGHT-155);
        g2d.drawString("Generation", BOX_WIDTH - 210, BOX_HEIGHT-155);
        g2d.drawString("Number of branches", BOX_WIDTH - 235, BOX_HEIGHT-50);
        g2d.setColor(Color.WHITE);
        g2d.drawString("0.4                                  0.64", 50, BOX_HEIGHT-165);
        g2d.drawString("0                   90                 180", 50, BOX_HEIGHT-65);

        //Draws the fractal
        fract.draw(g2d);
    }

    //Animation that runs at the start of the program
    public void startAnimation() {
        try{
            for (int i = 0; i<11; i++){
                TYPE = 2;

                generationSlider.jslider.setValue(i);
                GENERATION = i;

                lengthSlider.jslider.setValue(2*i + 40);
                LENGTH = (((double) i)/50) + 0.4 ;

                angleSlider.jslider.setValue(2*i/3);
                ANGLE = Math.toRadians(2*i);

                update();
                repaint();
                Thread.sleep(500);
            }
            startup = false;
        } catch (NullPointerException | InterruptedException e){}//double exception catch learned from https://www.journaldev.com/629/java-catch-multiple-exceptions-rethrow-exception
    }

    //Draws a new fractal taking in updated values for the four features of the fractal
    private void update() {
        fract = new Fractal2(TYPE, ANGLE, GENERATION, LENGTH);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        //set background color
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
        //draw the fractal
        draw(g2d);
    }

    public static void main(String args[]){
        JFrame frame = new JFrame("Fractal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main m = new Main();
        angleSlider = new AngleSlider(m);
        lengthSlider = new LengthSlider(m);
        generationSlider = new GenerationSlider(m);
        branchSlider = new BranchSlider(m);
        button = new Button(m);
        frame.setContentPane(m);
        frame.pack();
        frame.setVisible(true);

    }
}

