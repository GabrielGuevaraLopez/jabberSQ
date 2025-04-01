package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;

public class ExitCommand implements Command{
    private final Presentation presentation;

    public ExitCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
