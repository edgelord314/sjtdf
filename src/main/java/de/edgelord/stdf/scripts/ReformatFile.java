package de.edgelord.stdf.scripts;

import de.edgelord.stdf.reading.FileReader;
import de.edgelord.stdf.writing.FileWriter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReformatFile {

    public ReformatFile() {
    }

    /**
    * Makes the given file (use for .sdb only) easier readable for humans 
    * by adding proper word wraps and spaces
    *
    * @param  file  the file which should be reformatted, use .sdb files only!
    */
    public static void reformat(File file) throws IOException {

        FileReader fileReader = new FileReader(file);
        FileWriter fileWriter = new FileWriter(file);

        String content = fileReader.readFile();

        StringBuilder reformattedContent = new StringBuilder();

        /*
            Adding word wraps where necessary
         */

        int index = 0;
        boolean isInTagEnd = false;

        while (index < content.length()){

            char currentChar = content.charAt(index);

            if (currentChar == '}') {
                reformattedContent.append("}\n\n");
            } else if (currentChar == '(') {

                if (content.charAt(index + 1) == '*') {
                    isInTagEnd = true;
                } else {

                    isInTagEnd = false;
                }

                reformattedContent.append("(");
            } else if (currentChar == ')') {

                if (isInTagEnd){

                    reformattedContent.append(")\n\n");
                } else {

                    reformattedContent.append(")");
                }
            } else {

                reformattedContent.append(currentChar);
            }

            index++;
        }

        fileWriter.writeThrough(reformattedContent.toString());

        /*
            Adding spaces where necessary
         */

        Scanner scanner = new Scanner(file);

        reformattedContent = new StringBuilder((int) (content.length() + (content.length() * 0.1)));
        String currentLine = "";
        int currentLevel = 0;

        while (scanner.hasNextLine()){

            currentLine = scanner.nextLine();

            if (currentLine.startsWith("{")){

                if (currentLevel != 0){

                    currentLevel = currentLevel - 4;
                }
            } else {
                currentLine = getSpaces(currentLevel) + currentLine;
            }

            if (currentLine.startsWith(getSpaces(currentLevel) + "{*")){

                if (currentLevel != 0){

                    currentLevel = currentLevel - 4;
                }
            } else if (currentLine.startsWith(getSpaces(currentLevel) + "{")){

                currentLevel = currentLevel + 4;
            }

            reformattedContent.append(currentLine).append("\n");
        }

        fileWriter.writeThrough(reformattedContent.toString());
    }

    private static String getSpaces(int number){

        int index = 0;

        String spaces = "";

        while (index < number){

            spaces += " ";

            index++;
        }

        return spaces;
    }
}
