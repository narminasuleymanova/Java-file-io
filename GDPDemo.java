import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class GDPDemo {
    public static void main(String[] args) {
        List<Country> countries = FileManager.loadCountries(); //testing loadcountries method
        String correctInput = "Rank, 1,Azerbaijan,Asia,10139177.0,46491003031.29,40747787724.02,4552.60,4012.92"; // for testing parse method
        String incorrectInput = "Rank, 1,Azerbaijan,Asia,10139177.0,46491003031.29,40747787724.02,4552.60";

        try { //testing parseFrom with correct input
            Country correctCountry = Country.parseFrom(correctInput);
            System.out.println("Input parsed successfully: " + correctCountry.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Error parsing input: " + e.getMessage());
        }
        
        try { //testing parseFrom with incorrect input
            Country incorrectCountry = Country.parseFrom(incorrectInput);
            System.out.println("Input parsed successfully: " + incorrectCountry.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Error parsing input: " + e.getMessage());
        }

        Country country = new Country("0", "United States", "North America", Double.valueOf("328200000"), 
                              Double.valueOf("21439453.04"), Double.valueOf("21439453.04"), 
                              Double.valueOf("65297.51681"), Double.valueOf("65297.51681"));

        String countryRecord = Country.parseTo(country); //testing parseto method
        System.out.println(countryRecord);


        sort(countries, "continent", "asc"); //sort continents in ascending order
        sort(countries, "countryName", "asc"); //sort country names in ascending order

        try{FileManager.saveCountries(countries, "sorted_by_continent_and_country.csv"); //NOTE! WHEN RUNNING ALL THIS METHODS WILL GIVE ERROR
        //BECAUSE THE FILE ALREADY EXISTS!!

        //save to csv file, testing savecountries method
    } catch(FileNotFoundException e) {
        System.out.println("error with the file " + e.getMessage());
    }

        sort(countries, "population", "desc"); //sort by population in descending order

        try{FileManager.saveCountries(countries, "sorted_by_population.csv"); 
        //save to csv file
} catch(FileNotFoundException e) {
    System.out.println("error with the file " + e.getMessage());
}


        String continent = "Oceania"; // example continent
        List<Country> filteredByContinent = filterByContinent(countries, continent);

        // save filtered countries to file
        String continentFileName = "countries_in_" + continent.toLowerCase() + ".csv";
        try{
            FileManager.saveCountries(filteredByContinent, continentFileName);
        }catch(FileNotFoundException e) {
            System.out.println("error with the file " + e.getMessage());
        }

        // filter countries by per capita GDP range
        Double lower = 40000.0; // example lower limit
        Double upper = 50000.0; // example upper limit
        List<Country> filteredByPerCapita = filterByPerCapita(countries, lower, upper);

        // save filtered countries to file
        String perCapitaFileName = "countries_with_per_capita_gdp_between_" + lower + "_and_" + upper + ".csv";
        try{
            FileManager.saveCountries(filteredByPerCapita, perCapitaFileName);
        }catch(FileNotFoundException e) {
            System.out.println("error with the file " + e.getMessage());
        }

    }

    public static void sort(List<Country> countries, String fieldName, String order) {

        if(fieldName.equals("id")) {
            countries.sort((a, b) -> order.equals("asc") 
            ? a.getId().compareTo(b.getId()) 
            : b.getId().compareTo(a.getId()));
        }

        else if(fieldName.equals("countryName")) {
            countries.sort((a, b) -> order.equals("asc") 
            ? a.getCountryName().compareTo(b.getCountryName()) 
            : b.getCountryName().compareTo(a.getCountryName()));
        }

        else if(fieldName.equals("continent")) {
            countries.sort((a, b) -> order.equals("asc") 
            ? a.getContinent().compareTo(b.getContinent()) 
            : b.getContinent().compareTo(a.getContinent()));
        }

        else if(fieldName.equals("population")) {
            countries.sort((a, b) -> order.equals("asc") 
            ? a.getPopulation().compareTo(b.getPopulation()) : 
            b.getPopulation().compareTo(a.getPopulation()));
        }

        else if(fieldName.equals("IMF_GDP")) {
            countries.sort((a, b) -> order.equals("asc") 
            ? a.getIMF_GDP().compareTo(b.getIMF_GDP()) 
            : b.getIMF_GDP().compareTo(a.getIMF_GDP()));
        }

        else if(fieldName.equals("UN_GDP")) {
            countries.sort((a, b) -> order.equals("asc") 
            ? a.getUN_GDP().compareTo(b.getUN_GDP()) 
            : b.getUN_GDP().compareTo(a.getUN_GDP()));
        }

        else if(fieldName.equals("GDP_per_capita")) {
            countries.sort((a, b) -> order.equals("asc") 
            ? a.getIMF_GDP_per_capita().compareTo(b.getIMF_GDP_per_capita()) 
            : b.getIMF_GDP_per_capita().compareTo(a.getIMF_GDP_per_capita()));
        }

        else if(fieldName.equals("UN_GDP_per_capita")) {
            countries.sort((a, b) -> order.equals("asc") 
            ? a.getUN_GDP_per_capita().compareTo(b.getUN_GDP_per_capita()) 
            : b.getUN_GDP_per_capita().compareTo(a.getUN_GDP_per_capita()));
        }
    }

    public static List<Country> filterByContinent(List<Country> countries, String continent) {
        return countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .collect(Collectors.toList());
    }

    public static List<Country> filterByPerCapita(List<Country> countries, Double lower, Double upper) {
        if (lower > upper) {
            throw new IllegalArgumentException("Lower limit is greater than upper limit.");
        }

        return countries.stream()
                .filter(country -> country.getUN_GDP_per_capita() >= lower && country.getUN_GDP_per_capita() <= upper)
                .collect(Collectors.toList());
    }

}
