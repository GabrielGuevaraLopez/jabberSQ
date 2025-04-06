package org.nhlstenden.jabberpoint.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GoToTest {
  private GoTo goToCommand;
  private Presentation presentation;

  @BeforeEach
  void setUp() {
    presentation = new Presentation();
    goToCommand = new GoTo(presentation);
  }

  @Test
  void testConstructor() {
    GoTo command = new GoTo(presentation);
    assertNotNull(command);

    Presentation pres = new Presentation();
    GoTo command2 = new GoTo(pres);
    assertNotNull(pres);
    assertNotNull(command2);
  }

  @Test
  void testGoToValidSlide() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    presentation.setSlideNumber(0);
    assertDoesNotThrow(() -> goToCommand.execute());
  }

  @Test
  void testGoToBoundaryMin() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    presentation.setSlideNumber(-1);
    assertDoesNotThrow(() -> goToCommand.execute());
  }

  @Test
  void testGoToBoundaryMax() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    presentation.setSlideNumber(Integer.MAX_VALUE);
    assertDoesNotThrow(() -> goToCommand.execute());
  }
}
