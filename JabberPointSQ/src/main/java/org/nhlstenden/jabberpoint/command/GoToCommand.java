package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;
import org.nhlstenden.jabberpoint.util.Constants;

import javax.swing.*;

public class GoToCommand implements Command{
    private final Presentation presentation;

    public GoToCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        SlideViewerComponent parent = this.presentation.getParent();
        String pageNumberStr = JOptionPane.showInputDialog("Page number?");
        int pageNumber = 0;

        try {
            pageNumber = Integer.parseInt(pageNumberStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    parent, Constants.INT_ERR, Constants.JAB_ERR, JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (pageNumber <= 0) {
            JOptionPane.showMessageDialog(
                    parent, Constants.INT_ERR, Constants.JAB_ERR, JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.presentation.setSlideNumber(pageNumber - 1);
    }
}
