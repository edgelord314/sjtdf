package de.edgelord.stdf.reading;

import de.edgelord.stdf.Species;
import de.edgelord.stdf.writing.DataWriter;

import java.io.File;
import java.io.IOException;

public class DataReader {

    private String fileContent;
    private String fileName;

    public static String SDB_FILE_EXTENSION = ".sdb";
    public static final String STDF_VERSION = "1.2";

    public DataReader(File file) throws IOException {

        this(new FileReader(file));
    }

    public DataReader(FileReader fileReader) throws IOException {

        fileContent = fileReader.readFile();

        if ((compareVersions(DataReader.STDF_VERSION, getFileVersion()) == -1 && !isBackwardsCompatible()) || (compareVersions(DataReader.STDF_VERSION, getFileVersion()) == 1 && !isForwardCompatible())) {
            throw new RuntimeException("The file " + fileReader.getFile().getName() + " i snot compatible with this version of stdf!");
        }

        if (maskSpaces()) {
            fileContent = fileContent.replaceAll(" ", "");
            fileContent = fileContent.replace("*_*", " ");
        }

        fileName = fileReader.getFile().getName().replace(SDB_FILE_EXTENSION, "");
    }

    /**
    * Returns a Species with the given name and content by finding it in the File. 
     *
     * @param  speciesName  the Species name 
     * @return      the Species with proper name and content
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

    private Species getSystemSpecies() {
        return getSpecies(DataWriter.SYSTEM_SPECIES);
    }

    public boolean maskSpaces() {
        if (getSystemSpecies().getTagValue(DataWriter.MASK_SPACES_TAG).equals("true")) {
            return true;
        }

        return false;
    }

    public String getFileVersion() {
        return getSystemSpecies().getTagValue("version");
    }

    public boolean isForwardCompatible() {
        if (getSystemSpecies().getTagValue(DataWriter.FORWARD_COMPATIBLE_TAG).equals("true")) {
            return true;
        }

        return false;
    }

    public boolean isBackwardsCompatible() {
        if (getSystemSpecies().getTagValue(DataWriter.BACKWARDS_COMPATIBLE_TAG).equals("true")) {
            return true;
        }

        return false;
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

    public static int compareVersions(String version1, String version2){

        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++){
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0){
                return compare;
            }
        }

        return 0;
    }
}
