package ve.com.phl.remesafacil.Main;


import ve.com.phl.remesafacil.BasePresenter;
import ve.com.phl.remesafacil.BaseView;

/**
 * Created by ghoss on 31/08/2018.
 */
public interface MainContract {
    interface View extends BaseView<Presenter> {
        void getUserInfo();
        void setUserInfo(String name);
        void setDate(String date);
        void setTasa(String value);
        void setUserTasa(String userTasa);
        void saveTasa(String tasa);
        void hideTasa();
        void showTasa();
        void launchRequest();
        void launchPayments();
    }
    interface Presenter extends BasePresenter<View> {
        boolean validName (String name);
        String validTasa (String value);
        void verifyAdmin(String user);
    }
}
