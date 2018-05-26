package de.edgelord.stdf.reading;

import de.edgelord.stdf.Species;

import java.io.File;
import java.io.IOException;

public class DataReader {

    private File file;
    private FileReader fileReader;
    private String fileContent;
    private String fileName;

    public DataReader(File file) throws IOException {

        this.file = file;
        this.fileReader = new FileReader(file);
        fileContent = fileReader.readFile();
        fileContent = fileContent.replaceAll(" ", "");
        fileName = file.getName().replace(".sdb", "");
    }

    public DataReader(FileReader fileReader) throws IOException {

        this.fileReader = fileReader;
        this.file = fileReader.getFile();
        fileContent = fileReader.readFile();
        fileContent = fileContent.replaceAll(" ", "");
        fileName = file.getName().replace(".sdb", "");
    }

    public Species getSpecies(String speciesName) {
        return new Species(speciesName, splitString(fileContent, getSpeciesBegin(speciesName), getSpeciesEnd(speciesName)));
    }

    public Species getMainSpecies() {
        return new Species(fileName, splitString(fileContent, getSpeciesBegin(fileName), getSpeciesEnd(fileName)));
    }

    private String getSpeciesBegin(String speciesName){

        return "{" + speciesName + "}";
    }

    private String getSpeciesEnd(String speciesName){

        return "{*" + speciesName + "}";
    }

    public String getTagValue(String tag){

        return splitString(fileContent, "(" + tag + ")", "(*" + tag + ")");
    }

    public String splitString(String base, String start, String end) {

        return base.substring(base.lastIndexOf(start) + start.length(), base.lastIndexOf(end));
    }
}
