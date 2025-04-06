package org.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;

class AboutBoxTest {

    @Test
    void testShowWithNullFrame() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected. Skipping test.");
            return;
        }

        assertDoesNotThrow(() -> AboutBox.show(null));
    }

    @Test
    void testShowWithValidFrame() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected. Skipping test.");
            return;
        }

        Frame frame = new Frame();
        assertDoesNotThrow(() -> AboutBox.show(frame));
    }
}
