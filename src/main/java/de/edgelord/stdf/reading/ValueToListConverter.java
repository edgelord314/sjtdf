package de.edgelord.stdf.reading;

import de.edgelord.stdf.Species;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ValueToListConverter {

    public ValueToListConverter() {
    }

    /**
    * Converts the value of the given tag in the given Species to a List of Strings
    * separated by the given String and returns it.
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted
    * @param  separator  the separator which divides the Strings.
    * @return      the List of Strings
    */
    public static List<String> convertToList(Species species, String tag, String separator){

        String[] arrayToAdd = species.getTagValue(tag).split(separator);

        return new LinkedList<>(Arrays.asList(arrayToAdd));
    }

    /**
     * Returns the list from {@link #convertToList(Species, String, String)} as an array.
     *
     * @param  species  the Species which contains the tag
     * @param  tag  the tag which should be converted
     * @param  separator  the separator which divides the Strings.
     * @return      the array of Strings
     */
    public static String[] convertToArray(Species species, String tag, String separator) {
        return (String[]) convertToList(species, tag, separator).toArray();
    }

    /**
    * Converts the value of the given tag in the given Species to a List of Integers
    * separated by the given String and returns it.
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted
    * @param  separator  the separator which divides the Integers.
    * @return      the List of ints
    */
    public static List<Integer> convertToIntegerList(Species species, String tag, String separator){

        List<Integer> list = new LinkedList<>();

        for(String string : convertToList(species, tag, separator)){

            list.add(Integer.parseInt(string));
        }

        return list;
    }

    /**
     * Returns the list from {@link #convertToIntegerList(Species, String, String)} as an array.
     *
     * @param  species  the Species which contains the tag
     * @param  tag  the tag which should be converted
     * @param  separator  the separator which divides the Strings.
     * @return      the array of ints
     */
    public static Integer[] convertToIntegerArray(Species species, String tag, String separator) {
        return (Integer[]) convertToIntegerList(species, tag, separator).toArray();
    }

    /**
    * Converts the value of the given tag in the given Species to a List of doubles
    * separated by the given String and returns it.
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted
    * @param  separator  the separator which divides the doubles.
    * @return      the List of doubles
    */
    public static List<Double> convertToDoubleList(Species species, String tag, String separator){

        List<Double> list = new LinkedList<>();

        for(String string : convertToList(species, tag, separator)){

            list.add(Double.parseDouble(string));
        }

        return list;
    }

    /**
     * Returns the list from {@link #convertToDoubleList(Species, String, String)} as an array.
     *
     * @param  species  the Species which contains the tag
     * @param  tag  the tag which should be converted
     * @param  separator  the separator which divides the Strings.
     * @return      the array of doubles
     */
    public static Double[] convertToDoubleArray(Species species, String tag, String separator) {
        return (Double[]) convertToDoubleList(species, tag, separator).toArray();
    }

    /**
    * Converts the value of the given tag in the given Species to a List of chars
    * separated by the given String and returns it.
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted
    * @param  separator  the separator which divides the chars.
    * @return      the List of chars
    */
    public static List<Character> convertToCharList(Species species, String tag, String separator){

        List<Character> list = new LinkedList<>();

        for(String string : convertToList(species, tag, separator)){

            list.add(string.toCharArray()[0]);
        }

        return list;
    }

    /**
     * Returns the list from {@link #convertToCharList(Species, String, String)} as an array.
     *
     * @param  species  the Species which contains the tag
     * @param  tag  the tag which should be converted
     * @param  separator  the separator which divides the Strings.
     * @return      the array of chars
     */
    public static Character[] convertToCharArray(Species species, String tag, String separator) {
        return (Character[]) convertToCharList(species, tag, separator).toArray();
    }

    /**
     * Converts the value of the given tag in the given Species to a List of doubles
     * separated by the given String and returns it.
     *
     * @param  species  the Species which contains the tag
     * @param  tag  the tag which should be converted
     * @param  separator  the separator which divides the doubles.
     * @return      the List of doubles
     */
    public static List<Float> convertToFloatList(Species species, String tag, String separator){

        List<Float> list = new LinkedList<>();

        for(String string : convertToList(species, tag, separator)){

            list.add(Float.valueOf(string));
        }

        return list;
    }

    /**
     * Returns the list from {@link #convertToFloatList(Species, String, String)} as an array.
     *
     * @param  species  the Species which contains the tag
     * @param  tag  the tag which should be converted
     * @param  separator  the separator which divides the Strings.
     * @return      the array of floats
     */
    public static Float[] convertToFloatArray(Species species, String tag, String separator) {
        return (Float[]) convertToFloatList(species, tag, separator).toArray();
    }
}
