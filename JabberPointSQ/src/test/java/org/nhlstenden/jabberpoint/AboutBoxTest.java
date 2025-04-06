package org.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Frame;

class AboutBoxTest {

    @Test
    void testShowWithNullFrame() {
        assertDoesNotThrow(() -> AboutBox.show(null));
    }

    @Test
    void testShowWithValidFrame() {
        Frame frame = new Frame();
        assertDoesNotThrow(() -> AboutBox.show(frame));
    }
}
