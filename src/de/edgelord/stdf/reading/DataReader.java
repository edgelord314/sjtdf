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

    /**
    * Returns a Species with the given name and content by finding it in the File. 
     *
     * @param  speciesName  the Species name 
     * @return      the Secies with proper name and content
     * @see         Species
     */
    public Species getSpecies(String speciesName) {
        return new Species(speciesName, splitString(fileContent, getSpeciesBegin(speciesName), getSpeciesEnd(speciesName)));
    }

    /**
     * Returns the "Main"species by finding the Species that name is the name of the file without the .sdb extension
    *
    * @return      the "Main"species with its proper content
    * @see         Species
    */
    public Species getMainSpecies() {
        return new Species(fileName, splitString(fileContent, getSpeciesBegin(fileName), getSpeciesEnd(fileName)));
    }

    private String getSpeciesBegin(String speciesName){

        return "{" + speciesName + "}";
    }

    private String getSpeciesEnd(String speciesName){

        return "{*" + speciesName + "}";
    }

    /**
    * Returns the value of a specific tag within the whole file. 
     *
    * @param  tag  the name of the tag whose value gets returned
    * @return      the value of the specific tag
    */
    public String getTagValue(String tag){

        return splitString(fileContent, "(" + tag + ")", "(*" + tag + ")");
    }

    private String splitString(String base, String start, String end) {

        return base.substring(base.lastIndexOf(start) + start.length(), base.lastIndexOf(end));
    }
}
