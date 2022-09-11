package com.example.demo.utils;

import com.example.demo.model.Catalog;
import com.example.demo.model.Curs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterr {

    public static void writeFile(List<Catalog> note, Curs curs) {
        File f = new File("Note curs " + curs.getNumeCurs() + ".txt");
        try {
            f.createNewFile();
            FileWriter w;
            w = new FileWriter(f);
            w.write(note.toString());
            w.write("\n");
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
