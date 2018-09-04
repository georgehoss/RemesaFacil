package ve.com.phl.remesafacil.Model;

/**
 * Created by ghoss on 02/09/2018.
 */
public class Tasa {
    public static final String DB_TASA = "tasa";
    public static final String TASA_ID = "id";
    public static final String TASA_VALUE = "value";
    public static final String TASA_DATE = "date";
    public static final String TASA_USER = "user";
    public static final String ID = "tasa_del_dia";

    private String id;
    private String value;
    private String date;
    private String user;

    public Tasa() {
    }

    public Tasa(String id, String value, String date, String user) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
