package de.edgelord.stdf.writing;

import de.edgelord.stdf.Species;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class DataWriter {

    private LinkedList<Species> speciesList = new LinkedList<>();
    private File file;
    private FileWriter fileWriter;
    private String syntax = "";

    public DataWriter(File file){

        this.file = file;
        this.fileWriter = new FileWriter(file);
    }

    public DataWriter(FileWriter fileWriter){

        this.file = fileWriter.getFile();
        this.fileWriter = fileWriter;
    }

    public void addSpecies(Species species){

        speciesList.add(species);
    }

    public void syncFile() throws IOException {

        for (Species species : speciesList){
            syntax += species.getSyntax();
        }

        fileWriter.writeThrough(syntax);
    }
}
