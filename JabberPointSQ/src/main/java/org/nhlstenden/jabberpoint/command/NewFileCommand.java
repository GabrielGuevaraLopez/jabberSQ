package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;

public class NewFileCommand implements Command{
    private final Presentation presentation;

    public NewFileCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        this.presentation.clear();
        this.presentation.setSlideNumber(0);
        this.presentation.getParent().repaint();
    }
}
