package factories;

import java.util.Random;

public class PersonFactory {
	
    private static final String[] FIRST_NAMES = {
            "Kendrick", "Freddie", "David", "Bob", "Thom", "Joni", "David", "Frank", "Adrianne"
        };

        private static final String[] LAST_NAMES = {
            "Lamar", "Mercury", "Bowie", "Dylan", "Yorke", "Mitchel", "Gilmour", "Ocean", "Lenker"
        };
	
	protected static String generateRandomName() {
        Random random = new Random();
        
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        
        return firstName + " " + lastName;
	}
}
