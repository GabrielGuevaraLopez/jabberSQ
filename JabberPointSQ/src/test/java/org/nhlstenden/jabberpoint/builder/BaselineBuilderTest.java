package org.nhlstenden.jabberpoint.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.nhlstenden.jabberpoint.slide.Slide;
import org.nhlstenden.jabberpoint.slide.BaseSlide;
import org.nhlstenden.jabberpoint.slide.item.TextItem;
import org.nhlstenden.jabberpoint.slide.item.SlideItem;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;

class BaselineBuilderTest {
  private BaselineBuilder builder;
  private Vector<SlideItem> items;

  @BeforeEach
  void setUp() {
    builder = new BaselineBuilder();
    items = new Vector<>();
    items.add(new TextItem(1, "Test Item"));
  }

  @Test
  void testConstructorNotNull() {
    assertNotNull(builder);
  }

  @Test
  void testBaselineCreatorSetup() {
    builder.baselineCreatorSetup("Test Title", items);
    BaseSlide result = builder.getResult();
    assertNotNull(result);
    assertTrue(result instanceof BaseSlide);
    assertFalse(items.isEmpty());
  }

  @Test
  void testGetResultNoSetup() {
    assertNull(builder.getResult());
  }

  @Test
  void testMediaCreatorSetups() {
    builder.videoCreatorSetup("Video Title", items, "test.mp4", 0, 0, 640, 480);
    BaseSlide videoResult = builder.getResult();
    assertNotNull(videoResult);
    assertTrue(videoResult instanceof Slide);

    builder = new BaselineBuilder();
    builder.animationCreatorSetup("Animation Title", items, "test.gif");
    BaseSlide animationResult = builder.getResult();
    assertNotNull(animationResult);
    assertTrue(animationResult instanceof Slide);
  }
}
