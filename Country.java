
public class Country {
    
    private String id;;
    private String countryName;
    private String continent;
    private Double population;
    private Double IMF_GDP;
    private Double UN_GDP;
    private Double IMF_GDP_per_capita;
    private Double UN_GDP_per_capita;

    public Country(String id, String countryName, String continent, Double population, Double IMF_GDP, Double UN_GDP, Double IMF_GDP_per_capita, Double UN_GDP_per_capita ) {
        this.id = id;
        this.countryName = countryName;
        this.continent = continent;
        this.population = population;
        this.IMF_GDP = IMF_GDP;
        this.UN_GDP = UN_GDP;
        this.IMF_GDP_per_capita = IMF_GDP_per_capita;
        this.UN_GDP_per_capita = UN_GDP_per_capita;

        if (population != null && UN_GDP != null) {
            this.UN_GDP_per_capita = UN_GDP / (population * 1000); //calculate UN_GDP_per_capita, multiply by 1000 to convert from thousands to units
        }

        
    }

    public String getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getContinent() {
        return continent;
    }

    public Double getPopulation() {
        return population;
    }

    public Double getIMF_GDP() {
        return IMF_GDP;
    }

    public Double getUN_GDP() {
        return UN_GDP;
    }

    public Double getIMF_GDP_per_capita() {
        return IMF_GDP_per_capita;
    }

    public Double getUN_GDP_per_capita() {
        return UN_GDP_per_capita;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public void setIMF_GDP(Double iMF_GDP) {
        IMF_GDP = iMF_GDP;
    }

    public void setUN_GDP(Double uN_GDP) {
        UN_GDP = uN_GDP;
    }

    public void setIMF_GDP_per_capita(Double iMF_GDP_per_capita) {
        IMF_GDP_per_capita = iMF_GDP_per_capita;
    }

    public void setUN_GDP_per_capita(Double uN_GDP_per_capita) {
        UN_GDP_per_capita = uN_GDP_per_capita;
    }

    public static Country parseFrom(String countryRecord) {
        String[] fields = countryRecord.split(",");
        if(fields.length < 9){
            throw new IllegalArgumentException("Invalid country record: " + countryRecord);
        }
        String id = fields[1];
        String countryName = fields[2];
        String continent = fields[3];
        Double population;
        Double IMF_GDP;
        Double UN_GDP;
        Double IMF_GDP_per_capita;
        Double UN_GDP_per_capita;
        try{
            population = Double.parseDouble(fields[4]);
            IMF_GDP = Double.parseDouble(fields[5]);
            UN_GDP = Double.parseDouble(fields[6]);
            IMF_GDP_per_capita = Double.parseDouble(fields[7]);
            UN_GDP_per_capita = Double.parseDouble(fields[8]);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid field in country record: " + countryRecord);
        }
    
        return new Country(id, countryName, continent, population, IMF_GDP, UN_GDP, IMF_GDP_per_capita, UN_GDP_per_capita);
    }
    
    
    
    

    public String parseTo() {
        return id + "," + countryName + "," + continent + "," + population + "," + IMF_GDP + "," + UN_GDP + "," + IMF_GDP_per_capita + "," + UN_GDP_per_capita;
    }
    

    public static String parseTo(Country countryInstance) {
        return countryInstance.parseTo();
    }
}