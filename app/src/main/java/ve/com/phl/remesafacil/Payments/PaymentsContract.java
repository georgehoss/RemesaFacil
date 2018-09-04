package ve.com.phl.remesafacil.Payments;

import ve.com.phl.remesafacil.BasePresenter;
import ve.com.phl.remesafacil.BaseView;

/**
 * Created by ghoss on 03/09/2018.
 */
public interface PaymentsContract {
    interface View extends BaseView<Presenter> {
        void getRequest();
        void getUserInfo();
    }
    interface Presenter extends BasePresenter<View> {}
}
