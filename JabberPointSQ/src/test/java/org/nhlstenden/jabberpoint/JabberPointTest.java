package org.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import org.nhlstenden.jabberpoint.decorator.Style;
import org.nhlstenden.jabberpoint.decorator.StyleComponent;
import org.nhlstenden.jabberpoint.decorator.FontSizeStyleDecorator;
import org.nhlstenden.jabberpoint.decorator.ColorStyleDecorator;

class JabberPointTest {
  private Presentation presentation;

  @BeforeEach
  void setUp() {
    presentation = new Presentation();
  }

  @Test
  void testMainWithDifferentInputs(@TempDir Path tempDir) throws IOException {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    String[] emptyArgs = new String[] {};
    assertDoesNotThrow(() -> JabberPoint.main(emptyArgs));

    Path xmlPath = tempDir.resolve("test.xml");
    String xmlContent =
        "<?xml version=\"1.0\"?>\n"
            + "<presentation>\n"
            + "<showtitle>Test Presentation</showtitle>\n"
            + "<slide><title>Test Slide</title></slide>\n"
            + "</presentation>";
    Files.writeString(xmlPath, xmlContent);
    String[] validArgs = new String[] {xmlPath.toString()};
    assertDoesNotThrow(() -> JabberPoint.main(validArgs));

    // Test with invalid file
    String[] invalidArgs = new String[] {"nonexistent.xml"};
    assertDoesNotThrow(() -> JabberPoint.main(invalidArgs));
  }

  @Test
  void testStyleDecoration() {
    Style baseStyle = new Style(20, Color.BLACK, 36, 10);
    StyleComponent decoratedStyle =
        new FontSizeStyleDecorator(new ColorStyleDecorator(baseStyle, Color.GREEN), 48);

    assertNotNull(decoratedStyle);
    assertTrue(decoratedStyle instanceof FontSizeStyleDecorator);
  }

  @Test
  void testConstants() {
    assertEquals("IO Error: ", JabberPoint.IOERR);
    assertEquals("Jabberpoint Error ", JabberPoint.JABERR);
    assertEquals("Jabberpoint 1.6 - OU version", JabberPoint.JABVERSION);
  }
}
