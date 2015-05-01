package br.com.delogic.jnerator.impl.generator;

import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class CharacterAttributeGenerator implements AttributeGenerator {

    private String possibleChars;
    private Random random = new Random();

    public CharacterAttributeGenerator() {
        possibleChars = "abcdefghijklmnopqrstuvwxyz";
        possibleChars += possibleChars.toUpperCase();
        possibleChars += "0123456789";
    }

    public <T> Character generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return possibleChars.charAt(random.nextInt(possibleChars.length()));
    }

}
