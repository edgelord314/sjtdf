package de.edgelord.stdf.reading;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

    private File file;

    public FileReader(File file) {
        this.file = file;
    }
    
    /**
    * Returns the whole content of the file given in the constructor, by using a Scanner
    * and appending each line to a StringBuilder. 
    *
    * @return      the content of the file
    */
    
    public String readFile() throws IOException {

        return Files.lines(file.toPath(), StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
