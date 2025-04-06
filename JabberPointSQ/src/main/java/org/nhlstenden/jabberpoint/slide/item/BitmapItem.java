package org.nhlstenden.jabberpoint.slide.item;

import org.nhlstenden.jabberpoint.decorator.StyleComponent;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;

/**
 * The class for a bitmap item/p>
 *
 * <p>Bitmap items have the responsibility to draw themselves.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class BitmapItem extends SlideItem {
  protected BufferedImage bufferedImage;
  private String imageName;

  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";

  public BitmapItem(int level, String name) {
    super(level);
    imageName = name;
    try {
      if (name != null) {
        bufferedImage = ImageIO.read(new File(imageName));
      } else {
        bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
      }
    } catch (IOException e) {
      System.err.println(FILE + imageName + NOTFOUND);
      bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }
  }

  public BitmapItem(int level, BufferedImage image) {
    super(level);
    bufferedImage = image != null ? image : new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    imageName = null;
  }

  public BitmapItem() {
    this(0, (String) null);
  }

  public String getName() {
    return imageName;
  }

  @Override
  public Rectangle getBoundingBox(
      Graphics g, ImageObserver observer, float scale, StyleComponent style) {
    return new Rectangle(
        (int) (style.getIndent() * scale),
        0,
        (int) (bufferedImage.getWidth(observer) * scale),
        ((int) (style.getLeading() * scale)) + (int) (bufferedImage.getHeight(observer) * scale));
  }

  @Override
  public void draw(
      int x, int y, float scale, Graphics g, StyleComponent style, ImageObserver observer) {
    int width = x + (int) (style.getIndent() * scale);
    int height = y + (int) (style.getLeading() * scale);
    g.drawImage(
        bufferedImage,
        width,
        height,
        (int) (bufferedImage.getWidth(observer) * scale),
        (int) (bufferedImage.getHeight(observer) * scale),
        observer);
  }

  public String toString() {
    return "BitmapItem[" + getLevel() + "," + imageName + "]";
  }
}
