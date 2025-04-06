package org.nhlstenden.jabberpoint.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

  @BeforeEach
  void setUp() {
    Constants.loadConstants();
  }

  @Test
  void testConstantsAreLoaded() {
    assertNotNull(Constants.TEST_FILE);
    assertNotNull(Constants.DEFAULT_SAVE_PATH);
    assertNotNull(Constants.IO_ERR);
    assertNotNull(Constants.LOAD_ERR);
    assertNotNull(Constants.SAVE_ERR);
    assertNotNull(Constants.JAB_ERR);
    assertNotNull(Constants.TITLE_FRAME);
    assertNotNull(Constants.INT_ERR);
  }

  @Test
  void testResourceLoadingError() {
    assertNotNull(Constants.RESOURCE_LOADING_ERR);
    assertTrue(Constants.RESOURCE_LOADING_ERR.contains("mvn package"));
  }
}
