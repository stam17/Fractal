import java.awt.Graphics2D;

class Branch3 extends Branch2 implements Branch{

    //Inherits the two child branches from the parent class Branch2 and adds a middle branch
    Branch3 middle;

    //The methods below follow a similar pattern to those in the parent class
    //See class Branch2 for commented descriptions of each method

    public Branch3(int firstX1, int firstY1, int firstX2, int firstY2) {
        super(firstX1, firstY1, firstX2, firstY2);
        middle = null;
    }

    public Branch3(Branch3 prev, double angle, double len) {
        super(prev, angle, len);
        middle = null;
    }

    @Override
    public void nextGen(double angle, int gen, double len){
        if (generation < gen) {
            left = new Branch3(this, -angle, len);
            right = new Branch3(this, angle, len);
            middle = new Branch3(this, 0, len);
            left.nextGen(angle, gen, len);
            right.nextGen(angle, gen, len);
            middle.nextGen(angle, gen, len);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        if (this.middle!=null){
            middle.draw(g2d);
        }
    }

}


