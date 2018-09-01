package ve.com.phl.remesafacil.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ghoss on 01/09/2018.
 */
public class Utils {
    public static String getDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(" HH:mm dd/MM/yyyy"); //
        return dateFormat.format(new Date());
    }
}
