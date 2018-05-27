package de.edgelord.stdf;

import java.util.LinkedList;
import java.util.List;

public class Species {

    private String content;
    private String name;
    private List<Species> subSpecies = new LinkedList<>();

    public Species(String name, String content) {
        this.content = content;
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    /**
    * Returns the value of the given tag within the Species and all its subspecies
    *
    * @param  tag  the name of the tag whose value should be returned
    * @return  the value of the given tag
    */
    public String getTagValue(String tag) {

        return splitString(content, "(" + tag + ")", "(*" + tag + ")");
    }

    /**
    * Returns the Species with the given name within the Species
    *
    * @param  species  name of the Species wich should be returned and added to the list
    * @return  the Species with the given name
    */
    public Species getSubSpecies(String species) {

        Species speciesToReturn = new Species(species, splitString(content, "{" + species + "}", "{*" + species + "}"));

        return speciesToReturn;
    }

    private String splitString(String base, String start, String end) {

        return base.substring(base.lastIndexOf(start) + start.length(), base.lastIndexOf(end));
    }

    /**
    * Return the content of the Species and all its subspecies plus their head
    * {name} content {*name}
    *
    * @return  the complete syntax of the Species plus all its subspecies
    */
    public String getSyntax() {

        String syntax = "";

        syntax += "{" + name + "}" + content;

        for (Species species : subSpecies){

            syntax += species.getSyntax();
        }
        syntax += "{*" + name + "}";

        return syntax;
    }

    /**
    * Adds a tag with the given name and vaue to the Species
    * (name) value (*name)
    *
    * @param  tag  the name of the tag which should be added
    * @param  value  the value of the tag which should be added
    */
    public void addTag(String tag, String value) {

        content = "(" + tag + ")" + value + "(*" + tag + ")" + content;
    }

    /**
    * Creates the Species with the given name within the Species and adds it to the list,
    * which will be written to the file if requested and retuns it.
    *
    * @param  species name of the Species wich should be returned and added to the list
    * @return  the created Species with the given name
    */
    public Species addSubSpecies(String name) {

        Species speciesToReturn = new Species(name, "");

        subSpecies.add(speciesToReturn);
        return speciesToReturn;
    }
}
