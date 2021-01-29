import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Organisation[] orgs;
        orgs = readJSONtoArray("C:\\Users\\User\\IdeaProjects\\JSONReader\\src\\main\\java\\inputData.json");
        writeAllOrganisations(orgs);
        System.out.println("There are total "+countExpiredSecurities(orgs)+" expired securities");
        getOrganisationsAfterDate("13.12.2001", orgs);
        getCurrencySecurities(Currency.EU,orgs);
    }

    public static Organisation[] readJSONtoArray(String path) throws IOException {
        Organisation[] orgs;
        ObjectMapper mapper = new ObjectMapper();
        orgs = mapper.readValue(new File(path), Organisation[].class);
        return orgs;
    }

    public static void writeAllOrganisations(Organisation[] orgs){
        Arrays.stream(orgs)
                .forEach(x -> System.out.printf("\"%s\" - Foundation date %s\n",x.getOrgName(),x.getFoundationDate().format(DateTimeFormatter.ofPattern("dd/MM/yy"))));
    }

    public static int countExpiredSecurities(Organisation[] organisations){
        ArrayList<Securities> expiredSequrities =new ArrayList<>();
        Arrays.stream(organisations)
                .forEach(x->x.getSecurities()
                        .stream()
                        .filter(t->t.getExpiryDate().isBefore(LocalDate.now()))
                        .forEach(expiredSequrities::add));
        if (expiredSequrities.isEmpty()) System.out.println("There are no expired securities found.");
        else {
            System.out.println("Expired Securities: ");
            expiredSequrities.forEach(s->System.out.printf("Security ID: %s,\nSequrity Expiry Date: %s,\nSequrity Owner Company: %s.\n", s.getId(),s.getExpiryDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),s.getCompany()));
        }
        return expiredSequrities.size();
    }

    public static void getOrganisationsAfterDate (String date, Organisation[] organisations) {
        LocalDate ld;
        if (date.contains(".")) {
            if (date.charAt(date.length() - 3) == '.')
                ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yy"));
            else if (date.charAt(date.length() - 5) == '.')
                ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            else throw new IllegalArgumentException();
        } else if (date.contains("/")) {
            if (date.charAt(date.length() - 3) == '/')
                ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yy"));
            else if (date.charAt(date.length() - 5) == '/')
                ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        List<Organisation> selectedOrgs = Arrays.stream(organisations)
                .filter(x -> x.getFoundationDate().isAfter(ld)).collect(Collectors.toList());
        if (selectedOrgs.size() == 0) System.out.println("There are no organisations, found after "+ date+".");
        else {
            System.out.println("Organisations after "+date+": ");
            selectedOrgs.forEach(s -> System.out.printf("Organisation Name: %s,\nOrganisation Foundation Date: %s.\n", s.getOrgName(), s.getFoundationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        }
    }

    public static void getCurrencySecurities(Currency currency,Organisation[] organisations){
        if (currency!=Currency.EU && currency!=Currency.RU && currency!=Currency.USD) throw new IllegalArgumentException("You are using unsupported currency");
        ArrayList<Securities> currencySecurities = new ArrayList<>();
        Arrays.stream(organisations)
                .forEach(x->x.getSecurities()
                        .stream()
                        .filter(t->t.getCurrency().equals(currency))
                        .forEach(currencySecurities::add));
        if (currencySecurities.isEmpty()) System.out.println("There are no securities with "+currency+" currency found");
        else {
            System.out.println("Securities, supporting "+currency+" :");
            currencySecurities.forEach(s->System.out.printf("Security ID: %d\n",s.getId()));
        }
    }
}
