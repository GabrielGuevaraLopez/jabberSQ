package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.accessor.Accessor;
import org.nhlstenden.jabberpoint.accessor.XMLAccessor;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;
import org.nhlstenden.jabberpoint.util.Constants;

import javax.swing.*;
import java.io.IOException;

public class SaveFileCommand implements Command{
    private final Presentation presentation;

    public SaveFileCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        Accessor xmlAccessor = new XMLAccessor();
        SlideViewerComponent parent = this.presentation.getParent();

        try{
            xmlAccessor.saveFile(this.presentation, Constants.DEFAULT_SAVE_PATH);
        }catch (IOException exc){
            JOptionPane.showMessageDialog(parent, Constants.IO_ERR + exc, Constants.SAVE_ERR, JOptionPane.ERROR_MESSAGE);
        }
    }
}
