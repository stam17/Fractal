import java.awt.Graphics2D;


class Branch4 extends Branch2 implements Branch{

    //Inherits the two child branches from the parent class Branch2 and adds a two "middle" branches
    Branch4 leftMiddle;
    Branch4 rightMiddle;

    //The methods below follow a similar pattern to those in the parent class
    //See class Branch2 for commented descriptions of each method

    public Branch4(int firstX1, int firstY1, int firstX2, int firstY2) {
        super(firstX1, firstY1, firstX2, firstY2);
        leftMiddle = rightMiddle = null;
    }

    public Branch4(Branch4 prev, double angle, double len) {
        super(prev, angle, len);
        leftMiddle = rightMiddle = null;
    }

    @Override
    public void nextGen(double angle, int gen, double len){
        if (generation < gen) {
            left = new Branch4(this, -3*angle/2, len);
            right = new Branch4(this, 3*angle/2, len);
            leftMiddle = new Branch4(this, -angle/2, len);
            rightMiddle = new Branch4(this, angle/2, len);
            left.nextGen(angle, gen, len);
            right.nextGen(angle, gen, len);
            leftMiddle.nextGen(angle, gen, len);
            rightMiddle.nextGen(angle, gen, len);

        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        if (this.leftMiddle!=null){
            leftMiddle.draw(g2d);
        }
        if (this.rightMiddle!=null){
            rightMiddle.draw(g2d);
        }
    }

}


