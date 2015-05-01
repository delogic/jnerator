package br.com.delogic.jnerator.impl.generator;

import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class LoremIpsumAttributeGenerator implements AttributeGenerator<String> {

    private int    lenght;
    private Random random = new Random();

    public LoremIpsumAttributeGenerator(int lenght) {
        this.lenght = lenght;
    }

    public String generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        int begin = random.nextInt(LOREM_IPSUM.length());
        StringBuilder sb = new StringBuilder(LOREM_IPSUM.substring(begin));
        while (sb.length() < lenght) {
            sb.append(LOREM_IPSUM);
        }
        sb.delete(lenght, sb.length());
        return sb.toString();
    }

    public static String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
                                         + "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip "
                                         + "ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat "
                                         + "nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id "
                                         + "est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, tot"
                                         + "am rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. "
                                         + "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos "
                                         + "qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur"
                                         + ", adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat volupta"
                                         + "tem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea "
                                         + "commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae "
                                         + "consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";

}
