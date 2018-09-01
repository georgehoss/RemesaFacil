package ve.com.phl.remesafacil.Splash;

import ve.com.phl.remesafacil.BasePresenter;
import ve.com.phl.remesafacil.BaseView;

/**
 * Created by ghoss on 01/09/2018.
 */
public interface SplashContract {
    interface View extends BaseView<Presenter> {
        void launchSingUp();
        void launchSingIn();
    }
    interface Presenter extends BasePresenter<View> {}
}
