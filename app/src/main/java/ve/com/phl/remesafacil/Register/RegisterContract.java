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
        void showEmailFormatError();
        void showPswLengthError();
        void showPasswordError();
        void signUpUser(String firstName, String lastName, String number, String email, String password);
        void showEmailDuplicatedError();
        void showSingUpFailedError();
        void updateUserInfo(String name);
    }
    interface Presenter extends BasePresenter<View> {
        void register (String firstName, String lastName, String number, String email, String password);
        boolean validData(String firstName, String lastName, String number, String email, String password);
        boolean validEmail(CharSequence email);
        boolean validPsw (CharSequence psw);

    }
}
