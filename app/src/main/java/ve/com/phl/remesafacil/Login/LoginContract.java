package ve.com.phl.remesafacil.Login;

import ve.com.phl.remesafacil.BasePresenter;
import ve.com.phl.remesafacil.BaseView;

/**
 * Created by ghoss on 31/08/2018.
 */
public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void showUserError();
        void showPasswordError();
        void showLoginError();
    }
    interface Presenter extends BasePresenter<View> {
        void login(String user, String password);
        boolean validData(String user,String psw);
    }
}
