package org.nhlstenden.jabberpoint.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Font;
import java.awt.Color;

class FontStyleDecoratorTest {
    private Style baseStyle;
    private Font testFont;
    private float scale = 1.0f;

    @BeforeEach
    void setUp() {
        baseStyle = new Style(20, Color.BLACK, 36, 10);
        testFont = new Font("Arial", Font.BOLD, 24);
    }

    @Test
    void testConstructor() {
        FontStyleDecorator decorator = new FontStyleDecorator(baseStyle, testFont);
        assertNotNull(decorator);
    }

    @Test
    void testGetColor() {
        FontStyleDecorator decorator = new FontStyleDecorator(baseStyle, testFont);
        assertNull(decorator.getColor());
    }

    @Test
    void testGetFont() {
        FontStyleDecorator decorator = new FontStyleDecorator(baseStyle, testFont);
        assertNull(decorator.getFont(scale));
    }

    @Test
    void testGetIndent() {
        FontStyleDecorator decorator = new FontStyleDecorator(baseStyle, testFont);
        assertEquals(0, decorator.getIndent());
    }

    @Test
    void testGetLeading() {
        FontStyleDecorator decorator = new FontStyleDecorator(baseStyle, testFont);
        assertEquals(0, decorator.getLeading());
    }

    @Test
    void testCreateStyles() {
        FontStyleDecorator decorator = new FontStyleDecorator(baseStyle, testFont);
        assertDoesNotThrow(() -> decorator.createStyles());
    }

    @Test
    void testNullFont() {
        FontStyleDecorator decorator = new FontStyleDecorator(baseStyle, null);
        assertDoesNotThrow(() -> decorator.createStyles());
        assertNull(decorator.getFont(scale));
    }

    @Test
    void testDifferentFonts() {
        Font font1 = new Font("Arial", Font.BOLD, 24);
        Font font2 = new Font("Times New Roman", Font.PLAIN, 12);
        
        FontStyleDecorator decorator1 = new FontStyleDecorator(baseStyle, font1);
        FontStyleDecorator decorator2 = new FontStyleDecorator(baseStyle, font2);
        
        assertNull(decorator1.getFont(scale));
        assertNull(decorator2.getFont(scale));
    }
}
