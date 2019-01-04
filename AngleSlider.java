import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AngleSlider extends Slider {
    public AngleSlider(Main m) {
        super(m);
        jslider.setMaximum(30);
        jslider.setMinimum(0);
        jslider.setValue(10);
        jslider.setSnapToTicks(true);
        jslider.setPaintLabels(false);

        //Changes the branch angle of fractal object in the Main class according to the new value of the slider
        jslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slider = (JSlider) ce.getSource();
                if (!slider.getValueIsAdjusting()) {
                    Main.ANGLE = (Math.PI) * slider.getValue() / 30;
                }
            }
        });
        m.setLayout(null);
        jslider.setLocation(40, Main.BOX_HEIGHT - 120);
        jslider.setSize(200, 60);
        m.add(jslider);
    }
}
