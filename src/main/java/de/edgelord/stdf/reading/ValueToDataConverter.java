package de.edgelord.stdf.reading;

import de.edgelord.stdf.Species;

public class ValueToDataConverter {

    public ValueToDataConverter() {
    }

    /**
    * Converts the value of the given tag in the given Species to a boolean and returns it. 
    * If the value is "true", it returns true, if it's "false", it returns false.
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted 
    * @return      the converted boolean
    */
    public static boolean convertToBoolean(Species species, String tag) {

        if(species.getTagValue(tag).equals("true"))
            return true;
        return false;
    }

    /**
    * Converts the value of the given tag in the given Species to an Integer and returns it.
    * It simply parses the value by using Integer.parseInt().
    *
    * @param  species  the Species which contains the tag
    * @param  tag  the tag which should be converted 
    * @return      the converted int
    */
    public static int convertToInteger(Species species, String tag){
        try {
            return Integer.parseInt(species.getTagValue(tag));
        } catch(Exception e){
            throw e;
        }
    }

    /**
    * Converts the value of the given tag in the given Species to a double and returns it.
    * It simply parses the value by using Double.parseDouble().
    *
    * @param  species  the Species in which contains the tag
    * @param  tag  the tag which should be converted 
    * @return      the converted double
    */
    public static double convertToDouble(Species species, String tag) {
        try {
            return Double.parseDouble(species.getTagValue(tag));
        } catch(Exception e){
            throw e;
        }
    }

    /**
    * Converts the value of the given tag in the given Species to a short and returns it.
    * It simply parses the value by using Short.parseShort().
    *
    * @param  species  the Species in which contains the tag
    * @param  tag  the tag which should be converted 
    * @return      the converted short
    */
    public static short convertToShort(Species species, String tag) {
        try {
            return Short.parseShort(species.getTagValue(tag));
        } catch(Exception e){
            throw e;
        }
    }

    /**
    * Converts the value of the given tag in the given Species to a long and returns it
    * It simply parses the value by using Long.parseLong().
    *
    * @param  species  the Species in which contains the tag
    * @param  tag  the tag which should be converted 
    * @return      the converted long
    */
    public static long convertToLong(Species species, String tag) {
        try {
            return Long.parseLong(species.getTagValue(tag));
        } catch(Exception e){
            throw e;
        }
    }
    
    /**
    * Converts the value of the given tag in the given Species to a char and returns it
    * It converts the value into an Array of chars by calling String.toCharArray() and 
    * returns the first char in the list.
    *
    * @param  species  the Species in which contains the tag
    * @param  tag  the tag which should be converted 
    * @return      the converted char
    */
    public static char convertToChar(Species species, String tag) {
        try {
            return species.getTagValue(tag).toCharArray()[0];
        } catch(Exception e){
            throw e;
        }
    }
}
