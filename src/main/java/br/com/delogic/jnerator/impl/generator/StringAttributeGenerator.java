package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.util.Util;

public class StringAttributeGenerator implements AttributeGenerator {

    private String[][] values;

    public StringAttributeGenerator(String[]... values) {
        this.values = values;
    }

    public StringAttributeGenerator() {}

    public <T> String generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        if (values != null) {
            StringBuilder sb = new StringBuilder();
            for (String[] arr : values) {
                sb.append(arr[Util.nextInt(arr.length)]);
                sb.append(" ");
            }
            return sb.toString().trim();
        }

        return attributeConfiguration.getName() + " " + index;
    }

}
