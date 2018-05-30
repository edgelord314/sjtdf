package de.edgelord.stdf.reading;

import de.edgelord.stdf.Species;

import java.util.LinkedList;
import java.util.List;

public class ValueToListConverter {

    public ValueToListConverter() {
    }

    /**
    * Converts the value of the given tag in the given Species to a List of Strings
    * seperated by the given String and returns it.
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted
    * @param  seperator  the seperator which divides the Strings.
    * @return      the List of Strings
    */
    public static List<String> convertToList(Species species, String tag, String seperator){

        List<String> list = new LinkedList<>();
        String[] arrayToAdd = species.getTagValue(tag).split(seperator);

        int index = 0;
        for(String string : arrayToAdd){

            list.add(index, string);
            index++;
        }

        return list;
    }

    /**
    * Converts the value of the given tag in the given Species to a List of Integers
    * seperated by the given String and returns it.
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted
    * @param  seperator  the seperator which divides the Integers.
    * @return      the List of ints
    */
    public static List<Integer> convertToIntegerList(Species species, String tag, String seperator){

        List<Integer> list = new LinkedList<>();

        int index = 0;
        for(String string : convertToList(species, tag, seperator)){

            list.add(Integer.parseInt(string));
            index++;
        }

        return list;
    }

    /**
    * Converts the value of the given tag in the given Species to a List of doubles
    * seperated by the given String and returns it.
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted
    * @param  seperator  the seperator which divides the doubles.
    * @return      the List of doubles
    */
    public static List<Double> convertToDoubleList(Species species, String tag, String seperator){

        List<Double> list = new LinkedList<>();

        int index = 0;
        for(String string : convertToList(species, tag, seperator)){

            list.add(Double.parseDouble(string));
            index++;
        }

        return list;
    }

    /**
    * Converts the value of the given tag in the given Species to a List of chars
    * seperated by the given String and returns it.
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted
    * @param  seperator  the seperator which divides the chars.
    * @return      the List of chars
    */
    public static List<Character> convertToCharList(Species species, String tag, String seperator){

        List<Character> list = new LinkedList<>();

        int index = 0;
        for(String string : convertToList(species, tag, seperator)){

            list.add(string.toCharArray()[0]);
            index++;
        }

        return list;
    }
}
