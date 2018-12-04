package de.edgelord.stdf.reading;

import de.edgelord.stdf.Species;
import de.edgelord.stdf.writing.DataWriter;

import java.io.File;
import java.io.IOException;

public class DataReader {

    private String fileContent;
    private String fileName;
    private File file;

    public static String SDB_FILE_EXTENSION = ".sdb";
    public static final String STDF_VERSION = "1.3";

    private boolean containsSystemSpecies;

    public DataReader(File file) throws IOException {
        this(new FileReader(file));
    }

    public DataReader(FileReader fileReader) throws IOException {

        this.fileContent = fileReader.readFile();
        this.file = fileReader.getFile();

        containsSystemSpecies = containsSystemSpecies();

        if (containsSystemSpecies) {
            if ((compareVersions(DataReader.STDF_VERSION, getFileVersion()) == -1 && !isBackwardsCompatible()) || (compareVersions(DataReader.STDF_VERSION, getFileVersion()) == 1 && !isForwardCompatible())) {
                throw new RuntimeException("The file " + fileReader.getFile().getName() + " is not compatible with this version of stdf!");
            }

            if (shouldMaskSpaces()) {
                maskSpaces();
            }
        } else {
            maskSpaces();
        }

        fileName = fileReader.getFile().getName().replace(SDB_FILE_EXTENSION, "");
    }

    private void maskSpaces() {
        fileContent = fileContent.replaceAll(" ", "");
        fileContent = fileContent.replace("*_*", " ");
    }

    public DataWriter getDataWriter() throws IOException {
        return new DataWriter(file);
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

    private boolean containsSystemSpecies() {
        if (fileContent.contains(getAsSpeciesStart(DataWriter.SYSTEM_SPECIES)) && fileContent.contains(getAsSpeciesEnd(DataWriter.SYSTEM_SPECIES))) {

            if (fileContent.contains(getAsTagStart(DataWriter.MASK_SPACES_TAG)) && fileContent.contains(getAsTagEnd(DataWriter.MASK_SPACES_TAG))) {
                return true;
            }
        }

        return false;
    }

    public static String getAsSpeciesStart(String name) {
        return "{" + name + "}";
    }

    public static String getAsSpeciesEnd(String name) {
        return "{*" + name + "}";
    }

    public static String getAsTagStart(String name) {
        return "(" + name + ")";
    }

    public static String getAsTagEnd(String name) {
        return "(*" + name + ")";
    }

    private Species getSystemSpecies() {
        return getSpecies(DataWriter.SYSTEM_SPECIES);
    }

    public boolean shouldMaskSpaces() {
        if (containsSystemSpecies) {
            if (getSystemSpecies().getTagValue(DataWriter.MASK_SPACES_TAG).equals("true")) {
                return true;
            }
        }

        return false;
    }

    public String getFileVersion() {
        if (containsSystemSpecies) {
            return getSystemSpecies().getTagValue("version");
        }

        return "0.0.0";
    }

    public boolean isForwardCompatible() {
        if (containsSystemSpecies) {
            if (getSystemSpecies().getTagValue(DataWriter.FORWARD_COMPATIBLE_TAG).equals("true")) {
                return true;
            }
        }

        return false;
    }

    public boolean isBackwardsCompatible() {
        if (containsSystemSpecies) {
            if (getSystemSpecies().getTagValue(DataWriter.BACKWARDS_COMPATIBLE_TAG).equals("true")) {
                return true;
            }
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
