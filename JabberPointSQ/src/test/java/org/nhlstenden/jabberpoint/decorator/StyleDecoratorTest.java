package org.nhlstenden.jabberpoint.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.awt.Font;

class StyleDecoratorTest {
    private Style baseStyle;
    private float scale = 1.0f;

    @BeforeEach
    void setUp() {
        baseStyle = new Style(20, Color.BLACK, 36, 10);
    }

    @Test
    void testBaseStyles() {
        assertEquals(20, baseStyle.getIndent());
        assertEquals(Color.BLACK, baseStyle.getColor());
        assertEquals(10, baseStyle.getLeading());
        
        Style style = new Style(30, Color.RED, 48, 15);
        assertEquals(30, style.getIndent());
        assertEquals(Color.RED, style.getColor());
        assertEquals(15, style.getLeading());
    }

    @Test
    void testColorDecorator() {
        StyleComponent decorated = new ColorStyleDecorator(baseStyle, Color.RED);
        assertEquals(Color.RED, decorated.getColor());
        assertEquals(20, decorated.getIndent());
        assertEquals(10, decorated.getLeading());
        
        StyleComponent decorated2 = new ColorStyleDecorator(baseStyle, Color.BLUE);
        assertNotEquals(decorated.getColor(), decorated2.getColor());
    }

    @Test
    void testFontSizeDecorator() {
        StyleComponent decorated = new FontSizeStyleDecorator(baseStyle, 48);
        Font font = decorated.getFont(scale);
        assertEquals(48, font.getSize());
    }

    @Test
    void testFontSizeDecoratorMultipleSizes() {
        StyleComponent decorated1 = new FontSizeStyleDecorator(baseStyle, 48);
        StyleComponent decorated2 = new FontSizeStyleDecorator(baseStyle, 72);
        assertNotEquals(decorated1.getFont(scale).getSize(), 
                       decorated2.getFont(scale).getSize());
    }

    @Test
    void testMultipleDecorators() {
        StyleComponent decorated = new FontSizeStyleDecorator(
            new ColorStyleDecorator(baseStyle, Color.RED),
            48
        );
        assertEquals(Color.RED, decorated.getColor());
        assertEquals(48, decorated.getFont(scale).getSize());
        assertEquals(20, decorated.getIndent());
    }

    @Test
    void testDecoratorOrder() {
        StyleComponent order1 = new FontSizeStyleDecorator(
            new ColorStyleDecorator(baseStyle, Color.RED),
            48
        );
        StyleComponent order2 = new ColorStyleDecorator(
            new FontSizeStyleDecorator(baseStyle, 48),
            Color.RED
        );
        assertEquals(order1.getColor(), order2.getColor());
        assertEquals(order1.getFont(scale).getSize(), 
                    order2.getFont(scale).getSize());
    }

    @Test
    void testNegativeFontSize() {
        StyleComponent decorated = new FontSizeStyleDecorator(baseStyle, -10);
        Font font = decorated.getFont(scale);
        assertTrue(font.getSize() > 0);
    }

    @Test
    void testZeroScale() {
        Font font = baseStyle.getFont(0);
        assertTrue(font.getSize() > 0);
    }
}
