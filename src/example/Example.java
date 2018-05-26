package example;


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

    public static void main(String[] args){

        /*
        Write information to file
         */

        DataWriter dataWriter = new DataWriter(new File("/path/to/file.sdb"));

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
            ReformatFile.reformat(new File("/path/to/file.sdb"));
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
            dataReader = new DataReader(new File("/home/edgelord/sjgl testing/stdf/data.sdb"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Species reddenData = dataReader.getSpecies("data");
        Species reddenDataEntry = dataReader.getSpecies("dataEntry");

        System.out.println("number: " + reddenData.getTagValue("number"));
        System.out.println("text: " + reddenData.getTagValue("text"));
        System.out.println("combination: " + reddenData.getTagValue("combination"));
        System.out.println("health: " + reddenDataEntry.getTagValue("health"));
        System.out.println("score: " + reddenDataEntry.getTagValue("score"));
        System.out.println("highscore: " + reddenDataEntry.getTagValue("highscore"));

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

        int pi = ValueToDataConverter.convertToInteger(reddenData, "number");
        int score = ValueToDataConverter.convertToInteger(reddenDataEntry, "score");

        System.out.println("pi*score: " + (pi + score));

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
