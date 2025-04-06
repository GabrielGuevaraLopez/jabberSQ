package org.nhlstenden.jabberpoint.accessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.Slide;
import org.nhlstenden.jabberpoint.slide.item.TextItem;
import org.nhlstenden.jabberpoint.slide.item.BitmapItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest {
    private XMLAccessor accessor;
    private Presentation presentation;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        accessor = new XMLAccessor();
        presentation = new Presentation();
        
        String dtdContent = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<!ELEMENT presentation (showtitle,slide*)>\n" +
                "<!ELEMENT showtitle (#PCDATA)>\n" +
                "<!ELEMENT slide (title, item*)>\n" +
                "<!ELEMENT title (#PCDATA)>\n" +
                "<!ELEMENT item (#PCDATA)>\n" +
                "<!ATTLIST item kind CDATA #REQUIRED>\n" +
                "<!ATTLIST item level CDATA #REQUIRED>";
        Files.writeString(tempDir.resolve("jabberpoint.dtd"), dtdContent);
    }

    @Test
    void testLoadValidXMLFile() throws IOException {
        String validXml = "<?xml version=\"1.0\"?>\n" +
                "<!DOCTYPE presentation SYSTEM \"" + tempDir.resolve("jabberpoint.dtd") + "\">\n" +
                "<presentation>\n" +
                "<showtitle>Test Presentation</showtitle>\n" +
                "<slide>\n" +
                "<title>Test Slide</title>\n" +
                "<item kind=\"text\" level=\"1\">Test Item</item>\n" +
                "</slide>\n" +
                "</presentation>";
        
        Path xmlPath = tempDir.resolve("test.xml");
        Files.writeString(xmlPath, validXml);

        accessor.loadFile(presentation, xmlPath.toString());
        
        assertEquals("Test Presentation", presentation.getTitle());
        assertEquals(1, presentation.getSize());
        Slide slide = presentation.getSlide(0);
        assertNotNull(slide, "Slide should not be null");
        assertEquals("Test Slide", slide.getTitle());
    }

    @Test
    void testSaveAndLoadFile() throws IOException {
        Path imagePath = tempDir.resolve("test.jpg");
        Files.writeString(imagePath, "dummy image data");

        presentation.setTitle("Save Test");
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.append(new TextItem(1, "Test Text"));
        slide.append(new BitmapItem(2, imagePath.toString()));
        presentation.append(slide);

        Path savePath = tempDir.resolve("save-test.xml");
        accessor.saveFile(presentation, savePath.toString());

        assertTrue(Files.exists(savePath));
        String content = Files.readString(savePath);
        assertTrue(content.contains("Save Test"));
        assertTrue(content.contains("Test Slide"));

        Presentation loadedPresentation = new Presentation();
        accessor.loadFile(loadedPresentation, savePath.toString());

        assertEquals("Save Test", loadedPresentation.getTitle());
        assertEquals(1, loadedPresentation.getSize());
        assertNotNull(loadedPresentation.getSlide(0));
        assertEquals("Test Slide", loadedPresentation.getSlide(0).getTitle());
    }

    @Test
    void testLoadInvalidFile() {
        Path nonExistentFile = tempDir.resolve("nonexistent.xml");
        assertThrows(IOException.class, () -> 
            accessor.loadFile(presentation, nonExistentFile.toString())
        );
    }

}
