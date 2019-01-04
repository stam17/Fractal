import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

class Branch2 implements Branch {
    //Coordinates of the start and end points of the branch
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    //Specify length, angle, and color fo the branch
    public double length;
    public double myAngle;
    public Color myColor;

    //Keeps count of what generation of the fractal this branch is part of
    int generation;

    //Child branches stored by the current branch
    Branch2 left;
    Branch2 right;

    //Constructor used for creating the initial Branch object (parent branch)
    public Branch2(int firstX1, int firstY1, int firstX2, int firstY2) {
        this.x1 = firstX1;
        this.y1 = firstY1;
        this.x2 = firstX2;
        this.y2 = firstY2;
        this.length = Math.sqrt( ((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)) );
        if (x2!=x1) {
            this.myAngle = Math.atan((y2-y1)/(x2-x1));//
        } else {this.myAngle = 3*Math.PI/2;}//should stay as 3PI/2
        this.myColor = new Color(50, 25, 0);//brown = (50,25,0); forest green = (34,139,34)
        left = right = null;
        generation = 0;
    }

    //Constructor used for child branches, as it takes in the parent branch as a parameter
    public Branch2(Branch2 prev, double angle, double len) {
        this.x1 = prev.x2;
        this.y1 = prev.y2;
        this.length = len*prev.length;
        this.myAngle = prev.myAngle + angle;
        this.x2 = (int) (x1 + (length * Math.cos(myAngle)));
        this.y2 = (int) (y1 + (length * Math.sin(myAngle)));
        generation = prev.generation+1;
        this.myColor = new Color((Math.abs(prev.myColor.getRed()-2))%256, (Math.abs(prev.myColor.getGreen()+12))%256, (Math.abs(prev.myColor.getBlue()+3))%256 );
        left = right = null;
    }

    //Recursively instantiates branches of the fractal tree from the original branch
    public void nextGen(double angle, int gen, double len) {
        if (generation < gen) {
            left = new Branch2(this, -angle, len);
            right = new Branch2(this, angle, len);
            left.nextGen(angle, gen, len);
            right.nextGen(angle, gen, len);
        }
    }

    //Recursively follows the tree data structure and draws each branch accordingly
    //Based on the generation, it varies stroke thickness
    public void draw(Graphics2D g) {
        g.setColor(myColor);
        if (generation<4) {
            g.setStroke(new BasicStroke(5-generation)); //if generation is less than 4 draw thicker lines
            //setStroke learned from https://stackoverflow.com/questions/40645150/how-to-use-a-setstroke-on-a-graphics-object
        } else {
            g.setStroke(new BasicStroke(1)); // otherwise set stroke to 1
        }
        g.drawLine(x1, y1, x2, y2);
        if (this.left!=null){
            left.draw(g);
        }
        if (this.right!=null){
            right.draw(g);
        }
        g.setStroke(new BasicStroke(1));
    }


}