package de.edgelord.stdf.reading;

import de.edgelord.stdf.Species;

public class ValueToDataConverter {

    public ValueToDataConverter() {
    }

    public static boolean convertToBoolean(Species species, String tag) {

        if(species.getTagValue(tag).equals("true"))
            return true;
        return false;
    }

    public static int convertToInteger(Species species, String tag){
        try {
            return Integer.parseInt(species.getTagValue(tag));
        } catch(Exception e){
            throw e;
        }
    }

    public static double convertToDouble(Species species, String tag) {
        try {
            return Double.parseDouble(species.getTagValue(tag));
        } catch(Exception e){
            throw e;
        }
    }

    public static short convertToShort(Species species, String tag) {
        try {
            return Short.parseShort(species.getTagValue(tag));
        } catch(Exception e){
            throw e;
        }
    }

    public static long convertToLong(Species species, String tag) {
        try {
            return Long.parseLong(species.getTagValue(tag));
        } catch(Exception e){
            throw e;
        }
    }

    public static char convertToChar(Species species, String tag) {
        try {
            return species.getTagValue(tag).toCharArray()[0];
        } catch(Exception e){
            throw e;
        }
    }
}
