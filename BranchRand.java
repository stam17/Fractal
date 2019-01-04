import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


class BranchRand extends Branch2{
    static Random rand = new Random();

    //The methods below follow a similar pattern to those in the parent class
    //See class Branch for commented descriptions of each method

    public BranchRand(int firstX1, int firstY1, int firstX2, int firstY2) {
        super(firstX1, firstY1, firstX2, firstY2);
    }

    //Generates random values for color, branch angle, and branch length, within given bounds
    public BranchRand(BranchRand prev, double angle, double len) {
        super(prev, angle+((rand.nextInt(10)-5)/5), len+((rand.nextInt(10)-5))/4);
        this.myColor = new Color(rand.nextInt(254), rand.nextInt(254), rand.nextInt(254));
    }

    @Override
    public void nextGen(double angle, int gen, double len){
        if (generation < gen) {
            left = new BranchRand(this, -angle, len);
            right = new BranchRand(this, angle, len);
            left.nextGen(angle, gen, len);
            right.nextGen(angle, gen, len);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }

}


