package org.nhlstenden.jabberpoint.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.Slide;
import static org.junit.jupiter.api.Assertions.*;

class NextTest {
    private Next nextCommand;
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.append(new Slide());
        nextCommand = new Next(presentation);
    }

    @Test
    void testConstructor() {
        Next command = new Next(presentation);
        assertNotNull(command);
        
        Presentation pres = new Presentation();
        Next command2 = new Next(pres);
        assertNotNull(pres);
        assertNotNull(command2);
    }

    @Test
    void testNextSlideIncrementsSlideNumber() {
        presentation.setSlideNumber(0);
        nextCommand.execute();
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testNextSlideFromLastSlide() {
        int lastSlideIndex = presentation.getSize() - 1;
        presentation.setSlideNumber(lastSlideIndex);
        nextCommand.execute();
        assertEquals(lastSlideIndex, presentation.getSlideNumber());
    }
}
