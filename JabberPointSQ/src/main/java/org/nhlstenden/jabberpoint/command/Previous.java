package org.nhlstenden.jabberpoint.command;

public class Previous extends Command {

  public Previous(PresentationReceiver presentationReceiver) {
    super(presentationReceiver);
  }

  @Override
  public void execute() {
    presentationReceiver.previous();
  }
}
