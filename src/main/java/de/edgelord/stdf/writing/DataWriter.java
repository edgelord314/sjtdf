package de.edgelord.stdf.writing;

import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class DataWriter {

    private LinkedList<Species> speciesList = new LinkedList<>();
    private FileWriter fileWriter;
    private String syntax = "";
    private boolean maskSpaces = false;
    private boolean forwardCompatible = true;
    private boolean backwardsCompatible = true;

    public static final String SYSTEM_SPECIES = "stdf";
    public static final String MASK_SPACES_TAG = "mask-sapces";
    public static final String VERSION_TAG = "version";
    public static final String FORWARD_COMPATIBLE_TAG = "forward-compatible";
    public static final String BACKWARDS_COMPATIBLE_TAG = "backwards-compatible";

    public DataWriter(File file) throws IOException {
        this(new FileWriter(file));
    }

    public DataWriter(FileWriter fileWriter) throws IOException {

        if (!fileWriter.getFile().exists()) {
            fileWriter.getFile().createNewFile();
        }

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
    * Writes the content of all Species from the list and their
    * subspecies to the file. With that, all existing content of the file
    * will be overwritten.
    */
    public void syncFile() throws IOException {

        Species systemSpecies = new Species(SYSTEM_SPECIES);

        if (isMaksSpaces()) {
            systemSpecies.addTag(MASK_SPACES_TAG, "true");
        } else {
            systemSpecies.addTag(MASK_SPACES_TAG, "false");
        }

        if (isBackwardsCompatible()) {
            systemSpecies.addTag(BACKWARDS_COMPATIBLE_TAG, "true");
        } else {
            systemSpecies.addTag(BACKWARDS_COMPATIBLE_TAG, "false");
        }

        if (isForwardCompatible()) {
            systemSpecies.addTag(FORWARD_COMPATIBLE_TAG, "true");
        } else {
            systemSpecies.addTag(FORWARD_COMPATIBLE_TAG, "false");
        }

        systemSpecies.addTag(VERSION_TAG, DataReader.STDF_VERSION);

        syntax += systemSpecies.getSyntax();

        for (Species species : speciesList){
            syntax += species.getSyntax();
        }

        fileWriter.writeThrough(syntax);
    }

    public boolean isMaksSpaces() {
        return maskSpaces;
    }

    /**
     * Determines whether all spaces should be spaces if this file is being read, or all spaces should be deleted and
     * afterwards all "*_*" should be replaced with spaces. Use latter if you have just-visual-purpose spaces in your file.
     *
     * @param masSpaces true or false
     */
    public void setMaskSpaces(boolean masSpaces) {
        this.maskSpaces = masSpaces;
    }

    public boolean isForwardCompatible() {
        return forwardCompatible;
    }

    /**
     * Determines whether the file is compatible with newer versions of stdf or not.
     *
     * @param forwardCompatible true of false
     */
    public void setForwardCompatible(boolean forwardCompatible) {
        this.forwardCompatible = forwardCompatible;
    }

    public boolean isBackwardsCompatible() {
        return backwardsCompatible;
    }

    /**
     * Determines whether the file is compatible with older versions of stdf or not.
     *
     * @param backwardsCompatible true of false
     */
    public void setBackwardsCompatible(boolean backwardsCompatible) {
        this.backwardsCompatible = backwardsCompatible;
    }
}
