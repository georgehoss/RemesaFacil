package ve.com.phl.remesafacil.Register;

import ve.com.phl.remesafacil.BasePresenter;
import ve.com.phl.remesafacil.BaseView;

/**
 * Created by ghoss on 31/08/2018.
 */
public interface RegisterContract {
    interface View extends BaseView<Presenter> {}
    interface Presenter extends BasePresenter<View> {}
}
