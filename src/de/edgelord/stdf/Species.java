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

    public String getTagValue(String tag) {

        return splitString(content, "(" + tag + ")", "(*" + tag + ")");
    }

    public Species getSubSpecies(String species) {

        Species speciesToReturn = new Species(species, splitString(content, "{" + species + "}", "{*" + species + "}"));

        subSpecies.add(speciesToReturn);
        return speciesToReturn;
    }

    private String splitString(String base, String start, String end) {

        return base.substring(base.lastIndexOf(start) + start.length(), base.lastIndexOf(end));
    }

    public String getSyntax() {

        String syntax = "";

        syntax += "{" + name + "}" + content;

        for (Species species : subSpecies){

            syntax += species.getSyntax();
        }
        syntax += "{*" + name + "}";

        return syntax;
    }

    public void addTag(String tag, String value) {

        content = "(" + tag + ")" + value + "(*" + tag + ")" + content;
    }

    public Species addSubSpecies(String name) {

        Species speciesToReturn = new Species(name, "");

        subSpecies.add(speciesToReturn);
        return speciesToReturn;
    }
}
