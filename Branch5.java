import java.awt.Graphics2D;

class Branch5 extends Branch3 implements Branch{

    //Inherits the two child branches from the parent class Branch3 and adds two "middle" branches
    Branch5 leftMid;
    Branch5 rightMid;

    //The methods below follow a similar pattern to those in the parent class
    //See class Branch2 for commented descriptions of each method

    public Branch5(int firstX1, int firstY1, int firstX2, int firstY2) {
        super(firstX1, firstY1, firstX2, firstY2);
        leftMid = rightMid = null;
    }

    public Branch5(Branch5 prev, double angle, double len) {
        super(prev, angle, len);
        leftMid = rightMid = null;
    }

    @Override
    public void nextGen(double angle, int gen, double len){
        if (generation < gen) {
            left = new Branch5(this, -2*angle, len);
            right = new Branch5(this, 2*angle, len);
            middle = new Branch5(this, 0, len);
            leftMid = new Branch5(this, -angle, len);
            rightMid = new Branch5(this, angle, len);

            left.nextGen(angle, gen, len);
            right.nextGen(angle, gen, len);
            middle.nextGen(angle, gen, len);
            leftMid.nextGen(angle, gen, len);
            rightMid.nextGen(angle, gen, len);

        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if (this.middle!=null){
            leftMid.draw(g);
            rightMid.draw(g);
        }
    }

}


