package ve.com.phl.remesafacil;

/**
 * Created by ghoss on 31/08/2018.
 */
public interface BaseView<P extends BasePresenter> {

    void bindPresenter(P presenter);
}

