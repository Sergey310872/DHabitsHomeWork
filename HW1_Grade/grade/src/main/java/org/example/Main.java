package org.example;

import org.example.service.Menu;
import org.example.service.ReadWriteFile;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ReadWriteFile readWriteFile = new ReadWriteFile();
        readWriteFile.makeFile();
        new Menu().handlingMenu(readWriteFile);
        readWriteFile.saveFile();
    }
}