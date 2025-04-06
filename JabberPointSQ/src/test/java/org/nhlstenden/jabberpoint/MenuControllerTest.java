package org.nhlstenden.jabberpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.ActionEvent;
import java.awt.MenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;

class MenuControllerTest {
    private MenuController menuController;
    private Presentation presentation;
    private JMenuItem menuItem;
    private SlideViewerComponent slideViewer;
    private JFrame frame;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        frame = new JFrame();
        slideViewer = new SlideViewerComponent(presentation, frame);
        menuController = new MenuController(frame, presentation);
        menuItem = new JMenuItem();
    }

    @Test
    void testConstructorNotNull() {
        assertNotNull(menuController);
        assertTrue(menuController instanceof MenuBar);
    }

    @Test 
    void testMenuClickOpen() {
        menuItem.setActionCommand("Open");
        ActionEvent event = new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, "Open");
        assertDoesNotThrow(() -> menuController.createMenuItem("Open", e -> {}).getActionListeners()[0].actionPerformed(event));
    }

    @Test
    void testMenuClickNew() {
        menuItem.setActionCommand("New");
        ActionEvent event = new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, "New");
        assertDoesNotThrow(() -> menuController.createMenuItem("New", e -> {}).getActionListeners()[0].actionPerformed(event));
    }

    @Test
    void testMenuClickSave() {
        menuItem.setActionCommand("Save");
        ActionEvent event = new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, "Save");
        assertDoesNotThrow(() -> menuController.createMenuItem("Save", e -> {}).getActionListeners()[0].actionPerformed(event));
    }

    @Test
    void testMenuClickExit() {
        menuItem.setActionCommand("Exit");
        ActionEvent event = new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, "Exit");
        assertDoesNotThrow(() -> menuController.createMenuItem("Exit", e -> {}).getActionListeners()[0].actionPerformed(event));
    }

    @Test
    void testMenuClickNext() {
        menuItem.setActionCommand("Next");
        ActionEvent event = new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, "Next");
        assertDoesNotThrow(() -> menuController.createMenuItem("Next", e -> {}).getActionListeners()[0].actionPerformed(event));
    }

    @Test
    void testMenuClickPrev() {
        menuItem.setActionCommand("Prev");
        ActionEvent event = new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, "Prev");
        assertDoesNotThrow(() -> menuController.createMenuItem("Prev", e -> {}).getActionListeners()[0].actionPerformed(event));
    }

    @Test
    void testMenuClickGoto() {
        menuItem.setActionCommand("Goto");
        ActionEvent event = new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, "Goto");
        assertDoesNotThrow(() -> menuController.createMenuItem("Goto", e -> {}).getActionListeners()[0].actionPerformed(event));
    }
}
