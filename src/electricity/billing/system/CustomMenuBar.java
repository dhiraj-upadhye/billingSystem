package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class CustomMenuBar extends JMenuBar {

    private int height;

    public CustomMenuBar(int height) {
        this.height = height;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.height = height; // Set the desired height
        return size;
    }
}
