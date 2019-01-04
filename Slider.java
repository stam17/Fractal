import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

abstract class Slider {

    //Learned how to use sliders here: https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html

    JSlider jslider = new JSlider();

    public Slider(Main m) {
        jslider.setMaximum(10);
        jslider.setMinimum(0);
        jslider.setMinorTickSpacing(1);
        jslider.setMajorTickSpacing(2);
        jslider.setPaintTicks(true);
        jslider.setForeground(Color.WHITE);
        jslider.setPaintLabels(true);

        //Detects if a change has been made to the position of the slider
        jslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
            }
        });
    }
}
