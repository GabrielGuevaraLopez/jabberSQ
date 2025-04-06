package org.nhlstenden.jabberpoint.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

class IndentStyleDecoratorTest {
    private Style baseStyle;
    private float scale = 1.0f;

    @BeforeEach
    void setUp() {
        baseStyle = new Style(20, Color.BLACK, 36, 10);
    }

    @Test
    void testConstructor() {
        IndentStyleDecorator decorator = new IndentStyleDecorator(baseStyle, 30);
        assertNotNull(decorator);
    }

    @Test
    void testGetIndent() {
        IndentStyleDecorator decorator = new IndentStyleDecorator(baseStyle, 30);
        assertEquals(30, decorator.getIndent());
    }

    @Test
    void testCreateStyles() {
        IndentStyleDecorator decorator = new IndentStyleDecorator(baseStyle, 30);
        assertDoesNotThrow(() -> decorator.createStyles());
    }

    @Test
    void testMultipleIndents() {
        IndentStyleDecorator decorator1 = new IndentStyleDecorator(baseStyle, 30);
        IndentStyleDecorator decorator2 = new IndentStyleDecorator(baseStyle, 40);
        
        assertEquals(30, decorator1.getIndent());
        assertEquals(40, decorator2.getIndent());
    }

    @Test
    void testNegativeIndent() {
        IndentStyleDecorator decorator = new IndentStyleDecorator(baseStyle, -10);
        assertEquals(-10, decorator.getIndent());
    }

    @Test
    void testZeroIndent() {
        IndentStyleDecorator decorator = new IndentStyleDecorator(baseStyle, 0);
        assertEquals(0, decorator.getIndent());
    }

    @Test
    void testInheritedMethods() {
        IndentStyleDecorator decorator = new IndentStyleDecorator(baseStyle, 30);
        assertEquals(baseStyle.getColor(), decorator.getColor());
        assertEquals(baseStyle.getFont(scale), decorator.getFont(scale));
        assertEquals(baseStyle.getLeading(), decorator.getLeading());
    }
}
