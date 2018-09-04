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

    @Override
    public String validTasa(String value) {
        if (value.isEmpty())
            return "0";

        if (value.startsWith("."))
            value = "0"+value;

        return value;
    }

    @Override
    public void verifyAdmin(String user) {
        if (user.equals("ghoss@phl.com.ve"))
            mView.showTasa();
        else
            mView.hideTasa();
    }
}
