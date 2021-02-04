import Entities.Currency;
import Reader.JSONReader;

public class Main {

    public static void main(String[] args) {
        JSONReader reader = new JSONReader();
        reader.readJSONtoArray("C:\\Users\\User\\IdeaProjects\\JSONReader\\resources\\inputData.json");
        reader.writeAllOrganisations(reader.getOrgs());
        System.out.println("There are total "+reader.countExpiredSecurities(reader.getOrgs())+" expired securities");
        reader.getOrganisationsAfterDate("13.12.2001", reader.getOrgs());
        reader.getCurrencySecurities(Currency.EU,reader.getOrgs());
    }
}
