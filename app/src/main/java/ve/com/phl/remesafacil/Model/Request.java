package ve.com.phl.remesafacil.Model;

/**
 * Created by ghoss on 03/09/2018.
 */
public class Request {
    public static final String DB_REQUEST = "request";
    public static final int ST_SOLICITUD_ENVIADA = 100;
    public static final int ST_EN_ESPERA_DE_TRANSFERENCIA = 101;
    public static final int ST_TRANSFERENCIA_RECIBIDA = 102;
    public static final int ST_EN_PROCESO = 103;
    public static final int ST_COMPLETO = 104;
    public static final int ST_RECHAZADA = 105;
    public static final int ST_CANCELADA = 106;
    public static final int ST_INCOMPLETA = 107;

    private String id;
    private String userName;
    private String userEmail;
    private String bs;
    private String pc;
    private String accountNumber;
    private String accountName;
    private String accountId;
    private String observations;
    private String tasa;
    private String date;
    private int status;
    private String photoPC;
    private String photoBs;
    private String response;

    public Request() {
    }

    public Request(String id, String userName, String userEmail, String bs, String pc, String accountNumber, String accountName, String accountId, String observations, String tasa, String date) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.bs = bs;
        this.pc = pc;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountId = accountId;
        this.observations = observations;
        this.tasa = tasa;
        this.date = date;
        this.status = ST_SOLICITUD_ENVIADA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhotoPC() {
        return photoPC;
    }

    public void setPhotoPC(String photoPC) {
        this.photoPC = photoPC;
    }

    public String getPhotoBs() {
        return photoBs;
    }

    public void setPhotoBs(String photoBs) {
        this.photoBs = photoBs;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public String getTasa() {
        return tasa;
    }

    public void setTasa(String tasa) {
        this.tasa = tasa;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
