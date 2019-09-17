package controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Scanner;

public class UploadFileController {

    private JFileChooser chooser;
    private String title;
    private String content;

    public UploadFileController(){
        this.chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));

    }

    public void pickFile(){
        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                java.io.File myFile = chooser.getSelectedFile();
                this.title = myFile.getName();
                if (this.title.indexOf(".") > 0)
                    this.title = this.title.substring(0, this.title.lastIndexOf("."));
                Scanner input = new Scanner(myFile);

                StringBuilder content = new StringBuilder();
                while (input.hasNext()) {
                    content.append(input.nextLine());
                    content.append("\n");
                }
                input.close();
                this.content = content.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return  this.content;
    }
}
