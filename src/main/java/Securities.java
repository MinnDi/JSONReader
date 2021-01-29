import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Securities {
    private int id;
    private String company;
    private Currency currency;
    private double parValue;
    private int totalNumber;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id<100000 || id>999999) throw new IllegalArgumentException("Security ID is in wrong format, it should contain 6 digits");
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getParValue() {
        return parValue;
    }

    public void setParValue(double parValue) {
        if (parValue<=0) throw  new IllegalArgumentException("Par value is in wrong format, it should not be negative number or zero");
        this.parValue = parValue;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        if (totalNumber<=0) throw new IllegalArgumentException("Total number is in wrong format, it should not be negative number or zero");
        this.totalNumber = totalNumber;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate pd = LocalDate.parse(purchaseDate, dtf);
        if (pd.isAfter(LocalDate.now()) || (expiryDate!=null && pd.isAfter(expiryDate))) throw new IllegalArgumentException("Purchase date is in wrong format, it should not be after current date or after expiry date");
        this.purchaseDate = pd;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate ed = LocalDate.parse(expiryDate, dtf);
        if (ed.isBefore(purchaseDate) && purchaseDate!=null) throw new IllegalArgumentException("Expiry date is in wrong format, it should not be after current date or before purchase date");
        this.expiryDate = ed;
    }
}
