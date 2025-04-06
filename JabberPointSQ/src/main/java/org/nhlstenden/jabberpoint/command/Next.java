package org.nhlstenden.jabberpoint.command;

public class Next extends Command {

  public Next(PresentationReceiver presentationReceiver) {
    super(presentationReceiver);
  }

  @Override
  public void execute() {
    presentationReceiver.next();
  }
}
