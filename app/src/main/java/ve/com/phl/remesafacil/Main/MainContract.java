package ve.com.phl.remesafacil.Main;


import ve.com.phl.remesafacil.BasePresenter;
import ve.com.phl.remesafacil.BaseView;

/**
 * Created by ghoss on 31/08/2018.
 */
public interface MainContract {
    interface View extends BaseView<Presenter> {}
    interface Presenter extends BasePresenter<View> {}
}
