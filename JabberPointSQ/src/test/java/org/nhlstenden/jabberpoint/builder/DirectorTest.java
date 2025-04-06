package org.nhlstenden.jabberpoint.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.slide.BaseSlide;
import org.nhlstenden.jabberpoint.slide.item.TextItem;
import org.nhlstenden.jabberpoint.slide.item.SlideItem;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;

class DirectorTest {
  private Director director;
  private Builder builder;
  private Vector<SlideItem> items;

  @BeforeEach
  void setUp() {
    builder = new BaselineBuilder();
    director = new Director(builder);
    items = new Vector<>();
    items.add(new TextItem(1, "Test Item")); // Makes sure we have at least one item
  }

  @Test
  void testConstructorNotNull() {
    assertNotNull(director);
  }

  @Test
  void testGetBuilder() {
    assertEquals(builder, director.getBuilder());
  }

  @Test
  void testSetBuilder() {
    Builder newBuilder = new BaselineBuilder();
    director.setBuilder(newBuilder);
    assertEquals(newBuilder, director.getBuilder());
  }

  @Test
  void testChangeBuilder() {
    Builder newBuilder = new BaselineBuilder();
    director.changeBuilder(newBuilder);
    assertEquals(newBuilder, director.getBuilder());
  }

  @Test
  void testBuilderOperations() {
    Builder newBuilder = new BaselineBuilder();
    director.setBuilder(newBuilder);
    assertEquals(newBuilder, director.getBuilder());
  }

  @Test
  void testMakeSlides() {
    // Test base slide
    BaseSlide baseSlide = director.make("Test Title", items);
    assertNotNull(baseSlide);
    assertTrue(baseSlide instanceof BaseSlide);

    // Test animation slide
    BaseSlide animSlide = director.make("Animation Test", items, "test.gif");
    assertNotNull(animSlide);
    assertTrue(animSlide instanceof BaseSlide);

    // Test video slide
    BaseSlide videoSlide = director.make("Video Test", items, "test.mp4", 0, 0, 640, 480);
    assertNotNull(videoSlide);
    assertTrue(videoSlide instanceof BaseSlide);
  }
}
