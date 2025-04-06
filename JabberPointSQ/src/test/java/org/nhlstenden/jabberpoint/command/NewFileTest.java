package org.nhlstenden.jabberpoint.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;
import javax.swing.JFrame;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class NewFileTest {
  private NewFile newFileCommand;
  private Presentation presentation;

  @BeforeEach
  void setUp() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    presentation = new Presentation();
    JFrame frame = new JFrame();
    SlideViewerComponent slideViewer = new SlideViewerComponent(presentation, frame);
    presentation.setShowView(slideViewer);
    newFileCommand = new NewFile(presentation);
  }

  @Test
  void testConstructor() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    NewFile command = new NewFile(presentation);
    assertNotNull(command);
    assertNotNull(presentation);
  }

  @Test
  void testNewFileOperation() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    presentation.setTitle("Test");
    presentation.setSlideNumber(1);
    newFileCommand.execute();
    assertEquals(0, presentation.getSlideNumber());
    assertEquals(0, presentation.getSize());

    presentation.setSlideNumber(0);
    newFileCommand.execute();
    assertEquals(0, presentation.getSlideNumber());
    assertEquals(0, presentation.getSize());
  }
}
