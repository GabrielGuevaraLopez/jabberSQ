package org.nhlstenden.jabberpoint.slide;

import org.nhlstenden.jabberpoint.decorator.Style;
import org.nhlstenden.jabberpoint.decorator.StyleComponent;
import org.nhlstenden.jabberpoint.decorator.ColorStyleDecorator;
import org.nhlstenden.jabberpoint.decorator.FontSizeStyleDecorator;
import org.nhlstenden.jabberpoint.slide.item.SlideItem;
import org.nhlstenden.jabberpoint.slide.item.TextItem;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Vector;

/** <p>A slide. This class has a drawing functionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide implements BaseSlide {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    private String title;

    private Vector<SlideItem> items;

    public Slide() {
        items = new Vector<>();
        this.title = "Default title";
    }

    public Slide(String title, Vector<SlideItem> items){
        if (title.isEmpty()){
            this.title = "Default title";
        }
        else {
            this.title = title;
        }
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public Vector<SlideItem> getSlideItems() {
        return items;
    }

    public void setSlideItems(Vector<SlideItem> slideItems) {
        this.items = slideItems;
    }

    public int getNumberOfItems() {
        return items.size();
    }

    @Override
    public void append(SlideItem item) {
        items.addElement(item);
    }

    @Override
    public void append(int level, String message) {
        append(new TextItem(level, message));
    }

    private StyleComponent createStyle(int level) {
        Style baseStyle;
        switch (level) {
            case 0:
                baseStyle = new Style(0, Color.BLACK, 48, 20);
                return new ColorStyleDecorator(
                    new FontSizeStyleDecorator(baseStyle, 60),
                    Color.GREEN
                );
            case 1:
                baseStyle = new Style(20, Color.BLACK, 36, 10);
                return new ColorStyleDecorator(baseStyle, Color.RED);
            case 2:
                baseStyle = new Style(40, Color.BLACK, 30, 10);
                return new ColorStyleDecorator(baseStyle, Color.BLUE);
            case 3:
                baseStyle = new Style(60, Color.BLACK, 24, 10);
                return baseStyle;
            case 4:
                baseStyle = new Style(100, Color.ORANGE, 5, 20);
                return baseStyle;
            case 5:
                baseStyle = new Style(120, Color.PINK, 1, 20);
                return baseStyle;
            default:
                return new Style(60, Color.BLACK, 20, 10);
        }
    }

    @Override
    public void draw(Graphics graphics, Rectangle rectangle, ImageObserver imageObserver) {
        float scale = getScale(rectangle);
        int y = rectangle.y;
        // Title is handled separately
        SlideItem slideItem = new TextItem(0, this.getTitle());
        StyleComponent style = Style.getStyle(slideItem.getLevel());
        slideItem.draw(rectangle.x, y, scale, graphics, style, imageObserver);
        y += slideItem.getBoundingBox(graphics, imageObserver, scale, style).height;

        for (int number = 0; number< getNumberOfItems(); number++) {
            slideItem = getSlideItems().elementAt(number);
            style = createStyle(slideItem.getLevel());
            slideItem.draw(rectangle.x, y, scale, graphics, style, imageObserver);
            y += slideItem.getBoundingBox(graphics, imageObserver, scale, style).height;
        }
    }

    private float getScale(Rectangle area) {
        return Math.min(((float)area.width) / ((float)Slide.WIDTH), ((float)area.height) / ((float)Slide.HEIGHT));
    }
}