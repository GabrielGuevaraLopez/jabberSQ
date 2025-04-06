package org.nhlstenden.jabberpoint.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.nhlstenden.jabberpoint.Presentation;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.nio.file.Path;
import java.io.File;

class SaveTest {
  private Save saveCommand;
  private Presentation presentation;

  @BeforeEach
  void setUp() {
    presentation = new Presentation();
    saveCommand = new Save(presentation);
  }

  @Test
  void testConstructor() {
    assertNotNull(saveCommand);
    assertNotNull(presentation);
  }

  @Test
  void testSavePresentation(@TempDir Path tempDir) {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    File emptyFile = tempDir.resolve("test.xml").toFile();
    assertDoesNotThrow(() -> saveCommand.execute());

    presentation.setTitle("Test Presentation");
    File contentFile = tempDir.resolve("test-with-content.xml").toFile();
    assertDoesNotThrow(() -> saveCommand.execute());
  }
}
