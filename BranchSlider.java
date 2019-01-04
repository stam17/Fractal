import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BranchSlider extends Slider {
    public BranchSlider(Main m) {
        super(m);

        jslider.setValue(2);
        jslider.setMaximum(6);
        jslider.setMinimum(2);
        jslider.setMajorTickSpacing(1);
        jslider.setPaintTicks(true);
        jslider.setLabelTable(jslider.createStandardLabels(1,2));
        jslider.setPaintLabels(true);
        jslider.setSnapToTicks(true);

        //Changes the number of branches per intersection of fractal object in the Main class according to the new
        //value of the slider
        jslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slider = (JSlider) ce.getSource();
                if (!slider.getValueIsAdjusting()) {
                	Main.TYPE = slider.getValue();
                }
            }
        });
        m.setLayout(null);
        jslider.setLocation(Main.BOX_WIDTH - 280, Main.BOX_HEIGHT-120);
        jslider.setSize(200, 60);
        m.add(jslider);
    }
}
