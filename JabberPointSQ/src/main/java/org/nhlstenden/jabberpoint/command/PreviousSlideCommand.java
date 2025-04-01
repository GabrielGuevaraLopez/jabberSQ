package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;

public class PreviousSlideCommand implements Command{
    private final Presentation presentation;

    public PreviousSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.prevSlide();
    }
}
