package br.com.delogic.jnerator.impl.generator;

import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class CharacterAttributeGenerator implements AttributeGenerator<Character, Object> {

    private String possibleChars;
    private Random random = new Random();

    public CharacterAttributeGenerator() {
        possibleChars = "abcdefghijklmnopqrstuvwxyz";
        possibleChars += possibleChars.toUpperCase();
        possibleChars += "0123456789";
    }

    public Character generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return possibleChars.charAt(random.nextInt(possibleChars.length()));
    }

}
