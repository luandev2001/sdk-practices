package commons;

import org.apache.commons.text.RandomStringGenerator;

public class CommonsTextTest {
    public static void main(String[] args) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
        for (int i = 0; i < 50; i++) {
            System.out.println(generator.generate(6));
        }
    }
}
