import java.awt.Graphics2D;

public class Fractal2{
    private Branch2 tree;


    public Fractal2(int TYPE, double ANGLE, int GEN, double LENGTH){
        //Decide which type of fractal to draw (type varies by number of branches per intersection)
        if (TYPE == 2){
            tree = new Branch2(500,Main.BOX_HEIGHT,500,Main.BOX_HEIGHT-300);
        }
        else if (TYPE == 3){
            tree = new Branch3(500,Main.BOX_HEIGHT,500,Main.BOX_HEIGHT-300);
        }
        else if (TYPE == 4){
            tree = new Branch4(500,Main.BOX_HEIGHT,500,Main.BOX_HEIGHT-300);
        }
        else if (TYPE == 5){
            tree = new Branch5(500,Main.BOX_HEIGHT,500,Main.BOX_HEIGHT-300);
        }
        else if (TYPE == 6){
            tree = new BranchRand(500,Main.BOX_HEIGHT,500,Main.BOX_HEIGHT-300);
        } else {
            Main.showUsageAndExit();
        }
        tree.nextGen(ANGLE, GEN, LENGTH);
    }

    public void draw(Graphics2D g){
        tree.draw(g);
    }
}


