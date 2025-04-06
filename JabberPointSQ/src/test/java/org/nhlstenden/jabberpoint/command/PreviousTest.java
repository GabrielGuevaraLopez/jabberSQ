package org.nhlstenden.jabberpoint.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.Slide;
import static org.junit.jupiter.api.Assertions.*;

class PreviousTest {
    private Previous previousCommand;
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.append(new Slide());
        previousCommand = new Previous(presentation);
    }

    @Test
    void testConstructor() {
        assertNotNull(previousCommand);
        assertNotNull(presentation);
    }

    @Test
    void testPreviousSlideDecrementsSlideNumber() {
        presentation.setSlideNumber(1);
        previousCommand.execute();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testPreviousSlideFromFirstSlide() {
        presentation.setSlideNumber(0);
        previousCommand.execute();
        assertEquals(0, presentation.getSlideNumber());
    }
}
