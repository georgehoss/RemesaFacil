package ve.com.phl.remesafacil.Main;

/**
 * Created by ghoss on 01/09/2018.
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;


    MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void bindView(MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbindView() {
        mView =null;
    }

    @Override
    public boolean validName(String name) {
        return name!=null && name.length()>0;
    }
}
