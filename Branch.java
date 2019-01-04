import java.awt.Graphics2D;

interface Branch {

    //Recursively instantiates branches of the fractal tree from the original branch
    public void nextGen(double angle, int gen, double len);

    //Recursively follows the tree data structure and draws each branch accordingly
    //Based on the generation, it varies stroke thickness
    public void draw(Graphics2D g);


}