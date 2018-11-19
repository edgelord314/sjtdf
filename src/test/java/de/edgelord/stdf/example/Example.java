package de.edgelord.stdf.example;


import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.ValueToDataConverter;
import de.edgelord.stdf.reading.ValueToListConverter;
import de.edgelord.stdf.scripts.ReformatFile;
import de.edgelord.stdf.writing.DataWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Example {

    private static String pathToFile = "/Users/edgelord/file.sdb";

    public static void main(String[] args) throws IOException {

        /*
        Write information to file. Please specify with DataWriter#setMaskSpaces(boolean) whether all spaces should remain
        on the next reading from the file, or all spaces should be deleted and all "*_*" should be replaces with spaces afterwards.
        Use the latter for visual reasons.
         */

        DataWriter dataWriter = new DataWriter(new File(pathToFile));

        /*
        Use
        DataWriter#setForwardCompatible(boolean)
        and
        DataWriter#setBackwardsCompatible(boolean)
        to make sure that you file is always read correctly.
         */

        Species mainSpecies = new Species("data", "");

        mainSpecies.addTag("number", "314");
        mainSpecies.addTag("text", "pi");
        mainSpecies.addTag("combination", "pi = 314");

        Species dataEntry = mainSpecies.addSubSpecies("dataEntry");

        dataEntry.addTag("health", "31");
        dataEntry.addTag("score", "17");
        dataEntry.addTag("highscore", "9999999999");
        dataEntry.addTag("items", "bow,sword,shield,calculator");

        dataWriter.addSpecies(mainSpecies);

        try {
            dataWriter.syncFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        Make it human-readable by adding \n and spaces where necessary
         */

        try {
            ReformatFile.reformat(new File(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*

        File content is now:

        {data}

            (combination)pi = 314(*combination)

            (text)pi(*text)

            (number)314(*number)

        {dataEntry}

            (highscore)9999999999(*highscore)

            (score)17(*score)

            (health)31(*health)

        {dataEntry*}

        {data*}

         */

        /*
        Let's read the information
         */

        DataReader dataReader = null;

        try {
            dataReader = new DataReader(new File(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Species readData = dataReader.getSpecies("data");
        Species readDataEntry = dataReader.getSpecies("dataEntry");

        System.out.println("number: " + readData.getTagValue("number"));
        System.out.println("text: " + readData.getTagValue("text"));
        System.out.println("combination: " + readData.getTagValue("combination"));
        System.out.println("health: " + readDataEntry.getTagValue("health"));
        System.out.println("score: " + readDataEntry.getTagValue("score"));
        System.out.println("highscore: " + readDataEntry.getTagValue("highscore"));

        /*
        Output is now:

        number: 314
        text: pi
        combination: pi=314
        health: 31%
        score: 17
        highscore: 9999999999
         */

        /*
        But how do you work with that? The values are only Strings, and what's with that tag "items?"! Well, just watch:
         */

        int pi = ValueToDataConverter.convertToInteger(readData, "number");
        int score = ValueToDataConverter.convertToInteger(readDataEntry, "score");

        System.out.println("pi + score: " + (pi + score));

        /*
        You can convert into: boolean, int, double, short, long, char
        If you want to expand this selection, please go ahead, code it and pull-request it to my github repo: https://github.com/edgelord314/stdf/
         */

        List<String> items = ValueToListConverter.convertToList(dataEntry, "items", ",");

        System.out.print("List has " + items.size() + " items: ");

        for (String item : items){

            System.out.print(item + ", ");
        }

        /*
        You can convert into Lists of: Strings (convertToList()), int, double, char
        If you want to expand this selection, please go ahead, code it and pull-request it to my github repo: https://github.com/edgelord314/stdf/
         */

        /*
        Output of that all is now:

        number: 314
        text: pi
        combination: pi=314
        health: 31
        score: 17
        highscore: 9999999999
        pi*score: 331
        List has 4 items: bow, sword, shield, calculator,
         */

        /*
        If you want to improve stdf, or add new features, feel free to code it and pull-request it to my git repo:
        https://github.com/edgelord314/stdf/
         */
    }
}
