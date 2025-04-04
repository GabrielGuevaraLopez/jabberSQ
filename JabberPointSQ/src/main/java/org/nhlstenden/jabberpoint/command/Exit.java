package org.nhlstenden.jabberpoint.command;


public class Exit extends Command{

    public Exit(PresentationReceiver presentationReceiver)
    {
        super(presentationReceiver);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
