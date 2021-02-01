import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
