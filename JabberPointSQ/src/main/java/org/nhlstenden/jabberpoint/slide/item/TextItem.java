package org.nhlstenden.jabberpoint.slide.item;

import org.nhlstenden.jabberpoint.slide.Slide;
import org.nhlstenden.jabberpoint.decorator.StyleComponent;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * A text item.
 *
 * <p>A TextItem has drawingfunctionality.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class TextItem extends SlideItem {
  private String text;

  private static final String EMPTYTEXT = "No Text Given";

  public TextItem(int level, String string) {
    super(level);
    text = string;
  }

  public TextItem() {
    this(0, EMPTYTEXT);
  }

  public String getText() {
    return text == null ? "" : text;
  }

  public AttributedString getAttributedString(StyleComponent style, float scale) {
    AttributedString attrStr = new AttributedString(getText());
    attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
    return attrStr;
  }

  @Override
  public Rectangle getBoundingBox(
      Graphics g, ImageObserver observer, float scale, StyleComponent style) {
    List<TextLayout> layouts = getLayouts(g, style, scale);
    int xsize = 0, ysize = (int) (style.getLeading() * scale);
    Iterator<TextLayout> iterator = layouts.iterator();
    while (iterator.hasNext()) {
      TextLayout layout = iterator.next();
      Rectangle2D bounds = layout.getBounds();
      if (bounds.getWidth() > xsize) {
        xsize = (int) bounds.getWidth();
      }
      if (bounds.getHeight() > 0) {
        ysize += bounds.getHeight();
      }
      ysize += layout.getLeading() + layout.getDescent();
    }
    return new Rectangle((int) (style.getIndent() * scale), 0, xsize, ysize);
  }

  @Override
  public void draw(int x, int y, float scale, Graphics g, StyleComponent style, ImageObserver o) {
    if (text == null || text.length() == 0) {
      return;
    }
    List<TextLayout> layouts = getLayouts(g, style, scale);
    Point pen =
        new Point(x + (int) (style.getIndent() * scale), y + (int) (style.getLeading() * scale));
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(style.getColor());
    Iterator<TextLayout> it = layouts.iterator();
    while (it.hasNext()) {
      TextLayout layout = it.next();
      pen.y += layout.getAscent();
      layout.draw(g2d, pen.x, pen.y);
      pen.y += layout.getDescent();
    }
  }

  private List<TextLayout> getLayouts(Graphics g, StyleComponent s, float scale) {
    List<TextLayout> layouts = new ArrayList<TextLayout>();
    AttributedString attrStr = getAttributedString(s, scale);
    Graphics2D g2d = (Graphics2D) g;
    FontRenderContext frc = g2d.getFontRenderContext();
    LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    float wrappingWidth = (Slide.WIDTH - s.getIndent()) * scale;
    while (measurer.getPosition() < getText().length()) {
      TextLayout layout = measurer.nextLayout(wrappingWidth);
      layouts.add(layout);
    }
    return layouts;
  }

  public String toString() {
    return "TextItem[" + getLevel() + "," + getText() + "]";
  }
}
