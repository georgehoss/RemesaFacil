package ve.com.phl.remesafacil.Register;

import ve.com.phl.remesafacil.BasePresenter;
import ve.com.phl.remesafacil.BaseView;

/**
 * Created by ghoss on 31/08/2018.
 */
public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void showFirstNameError();
        void showLastNameError();
        void showNumberError();
        void showEmailError();
        void showPasswordError();
    }
    interface Presenter extends BasePresenter<View> {
        void register (String firstName, String lastName, String number, String email, String password);
        boolean validData(String firstName, String lastName, String number, String email, String password);

    }
}
