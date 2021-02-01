import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        JSONReader reader = new JSONReader();
        reader.orgs = reader.readJSONtoArray("C:\\Users\\User\\IdeaProjects\\JSONReader\\resources\\inputData.json");
        reader.writeAllOrganisations(reader.orgs);
        System.out.println("There are total "+reader.countExpiredSecurities(reader.orgs)+" expired securities");
        reader.getOrganisationsAfterDate("13.12.2001", reader.orgs);
        reader.getCurrencySecurities(Currency.EU,reader.orgs);
    }

}
