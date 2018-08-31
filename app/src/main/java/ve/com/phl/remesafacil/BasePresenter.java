package ve.com.phl.remesafacil;

/**
 * Created by ghoss on 31/08/2018.
 */


public interface BasePresenter<V extends BaseView> {

    void bindView(V view);
    void unbindView();
}