package org.nhlstenden.jabberpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

class KeyControllerTest {
  private KeyController keyController;
  private Presentation presentation;
  private JFrame frame;

  @BeforeEach
  void setUp() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    presentation = new Presentation();
    keyController = new KeyController(presentation);
    frame = new JFrame();
  }

  @Test
  void testConstructorNotNull() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    assertNotNull(keyController);
  }

  @Test
  void testKeyPressPageDown() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    KeyEvent event =
        new KeyEvent(
            frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_DOWN, 'P');
    assertDoesNotThrow(() -> keyController.keyPressed(event));
  }

  @Test
  void testKeyPressPageUp() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    KeyEvent event =
        new KeyEvent(
            frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_UP, 'P');
    assertDoesNotThrow(() -> keyController.keyPressed(event));
  }

  @Test
  void testKeyReleased() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    KeyEvent event =
        new KeyEvent(
            frame,
            KeyEvent.KEY_RELEASED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_PAGE_DOWN,
            'P');
    assertDoesNotThrow(() -> keyController.keyReleased(event));
  }

  @Test
  void testKeyTyped() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    KeyEvent event =
        new KeyEvent(
            frame, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'q');
    assertDoesNotThrow(() -> keyController.keyTyped(event));
  }
}
