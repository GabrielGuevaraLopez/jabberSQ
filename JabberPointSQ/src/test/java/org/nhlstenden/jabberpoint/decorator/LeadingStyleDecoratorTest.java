package org.nhlstenden.jabberpoint.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

class LeadingStyleDecoratorTest {
  private Style baseStyle;
  private float scale = 1.0f;

  @BeforeEach
  void setUp() {
    baseStyle = new Style(20, Color.BLACK, 36, 10);
  }

  @Test
  void testConstructor() {
    LeadingStyleDecorator decorator = new LeadingStyleDecorator(baseStyle, 15);
    assertNotNull(decorator);
  }

  @Test
  void testLeadingValues() {
    LeadingStyleDecorator decorator1 = new LeadingStyleDecorator(baseStyle, 15);
    LeadingStyleDecorator decorator2 = new LeadingStyleDecorator(baseStyle, 20);
    assertEquals(15, decorator1.getLeading());
    assertEquals(20, decorator2.getLeading());

    LeadingStyleDecorator zeroDecorator = new LeadingStyleDecorator(baseStyle, 0);
    assertEquals(0, zeroDecorator.getLeading());

    LeadingStyleDecorator negativeDecorator = new LeadingStyleDecorator(baseStyle, -5);
    assertEquals(-5, negativeDecorator.getLeading());
  }

  @Test
  void testInheritedMethods() {
    LeadingStyleDecorator decorator = new LeadingStyleDecorator(baseStyle, 15);
    assertEquals(baseStyle.getColor(), decorator.getColor());
    assertEquals(baseStyle.getFont(scale), decorator.getFont(scale));
    assertEquals(baseStyle.getIndent(), decorator.getIndent());
  }

  @Test
  void testCreateStyles() {
    LeadingStyleDecorator decorator = new LeadingStyleDecorator(baseStyle, 15);
    assertDoesNotThrow(() -> decorator.createStyles());
  }
}
