import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LengthSlider extends Slider {
    public LengthSlider(Main m) {
        super(m);

        jslider.setValue(52);
        jslider.setMaximum(64);
        jslider.setMinimum(40);
        jslider.setMinorTickSpacing(2);
        jslider.setMajorTickSpacing(4);
        jslider.setPaintTicks(true);
        jslider.setPaintLabels(false);

        //Changes the branch length of fractal object in the Main class according to the new value of the slider
        jslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slider = (JSlider) ce.getSource();
                if (!slider.getValueIsAdjusting()) {
                    Main.LENGTH = (double) slider.getValue() / 100.0;
                }
            }
        });
        m.setLayout(null);
        jslider.setLocation(40, Main.BOX_HEIGHT - 220);
        jslider.setSize(200, 60);
        m.add(jslider);
    }
}
