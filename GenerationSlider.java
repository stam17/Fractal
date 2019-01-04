import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GenerationSlider extends Slider {
    public GenerationSlider(Main m) {
        super(m);
        jslider.setLabelTable(jslider.createStandardLabels(1,0));
        jslider.setValue(0);
        jslider.setSnapToTicks(true);
        m.setLayout(null);
        jslider.setLocation(Main.BOX_WIDTH - 280, Main.BOX_HEIGHT - 220);
        jslider.setSize(200, 60);

        //Changes the generation of fractal object in the Main class according to the new value of the slider
        jslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slider = (JSlider) ce.getSource();
                if (!slider.getValueIsAdjusting()) {
                    Main.GENERATION = slider.getValue();
                }
            }
        });
        m.add(jslider);
    }
}
