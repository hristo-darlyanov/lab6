package common.model;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate adapter
 * Used to convert LocalDate to String and vice versa
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Converts a string to a LocalDate
     * @param v String to convert
     * @return LocalDate
     * @throws Exception If an error occurs
     */
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, formatter);
    }

    /**
     * Converts a LocalDate to a string
     * @param v LocalDate to convert
     * @return String
     * @throws Exception If an error occurs
     */
    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(formatter);
    }
}

