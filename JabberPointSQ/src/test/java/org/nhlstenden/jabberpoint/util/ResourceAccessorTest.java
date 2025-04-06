package org.nhlstenden.jabberpoint.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;

class ResourceAccessorTest {

  @BeforeEach
  void setUp() {
    Constants.loadConstants();
  }

  @Test
  void testGetResourceAsString() {
    String content = ResourceAccessor.getResourceAsString("constants.json");
    assertNotNull(content);
    assertTrue(content.contains("testFile"));
  }

  @Test
  void testNullResourcePath() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          ResourceAccessor.getResource(null);
        });
  }

  @Test
  void testInvalidResourcePath() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          ResourceAccessor.getResource("nonexistent.file");
        });
  }

  @Test
  void testResourceLoading() {
    InputStream stream = ResourceAccessor.getResource("constants.json");
    assertNotNull(stream);

    String content = ResourceAccessor.getResourceAsString("constants.json");
    assertNotNull(content);
    assertTrue(content.contains("testFile"));
  }
}
