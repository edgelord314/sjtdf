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

        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine())
            stringBuilder.append(scanner.nextLine());

        return stringBuilder.toString();
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
