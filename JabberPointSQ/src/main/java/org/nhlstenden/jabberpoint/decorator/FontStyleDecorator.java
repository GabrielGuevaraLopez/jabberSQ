package org.nhlstenden.jabberpoint.decorator;

import java.awt.*;

public class FontStyleDecorator extends StyleWrapper {
  private Font newFont;

  public FontStyleDecorator(StyleComponent wrappee, Font newFont) {
    super(wrappee);
    this.newFont = newFont;
  }

  @Override
  public Color getColor() {
    return null;
  }

  @Override
  public Font getFont(float scale) {
    return null;
  }

  @Override
  public int getIndent() {
    return 0;
  }

  @Override
  public int getLeading() {
    return 0;
  }

  @Override
  public void createStyles() {

    super.createStyles();
    applyFont();
  }

  private Font applyFont() {
    System.out.println("Applying font: " + newFont);
    return newFont;
  }
}
