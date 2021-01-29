import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Organisation {
    private String orgName;
    private String orgAddress;
    private String phoneNumber;
    private long itn;
    private long psrn;
    private LocalDate foundationDate;
    private ArrayList<Securities> securities = new ArrayList<>();

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[ )(-]", "");
        if (phoneNumber.length()!=12&&phoneNumber.charAt(0)=='+'&& !phoneNumber.matches("[+\\d{11}]")) throw new IllegalArgumentException("Phone number is in wrong format, ypu entered number "+ phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public long getItn() {
        return itn;
    }

    public void setItn(long itn) {
        if (itn>9999999999L||itn<1000000000L) throw new IllegalArgumentException("ITN is in wrong format, it should contain 10 digits");
        this.itn = itn;
    }

    public long getPsrn() {
        return psrn;
    }

    public void setPsrn(long psrn) {
        if (psrn>9999999999999L||psrn<1000000000000L) throw new IllegalArgumentException("PSRN is in wring format, it should contain 13 digits, you entered "+psrn);
        this.psrn = psrn;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(String foundationDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fd = LocalDate.parse(foundationDate, dtf);
        if (fd.isAfter(LocalDate.now())) throw  new IllegalArgumentException("Foundation date should be before current date");
        this.foundationDate = fd;
    }

    public ArrayList<Securities> getSecurities() {
        return securities;
    }
}
