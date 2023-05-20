import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final List<Country> COUNTRIES = loadCountries();
    private static final String FILENAME = "data/countries.csv";

    public static List<Country> loadCountries() {
        List<Country> countries = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String line = reader.readLine();

            // Skip the first line(header)
            line = reader.readLine();

            while (line != null) {
                Country country = Country.parseFrom(line);
                countries.add(country);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public static void saveCountries(List<Country> countries, String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (file.exists()) {
            throw new FileNotFoundException("File already exists: " + fileName);
        }
    
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file));
    
            writer.println("id,countryName,continent,population,IMF_GDP,UN_GDP,IMF_GDP_per_capita,UN_GDP_per_capita");
    
            for (Country country : countries) {
                String line = country.parseTo();
                writer.println(line);
            }
    
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
