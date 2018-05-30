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

    /**
    * Adds the given Species to the list of Species which will be written
    * to the file when requested
    *
    * @param  species  the Species which should be added to the list
    */
    public void addSpecies(Species species){

        speciesList.add(species);
    }

    /**
    * Writes the content off all Species from the list speciesList and their 
    * subspecies to the file file. With that, all existing content off the file
    * will be overwritten.
    */
    public void syncFile() throws IOException {

        for (Species species : speciesList){
            syntax += species.getSyntax();
        }

        fileWriter.writeThrough(syntax);
    }
}
