package ve.com.phl.remesafacil.Login;

/**
 * Created by ghoss on 31/08/2018.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    LoginPresenter(LoginContract.View view)
    {
        bindView(view);
    }

    @Override
    public void login(String user, String password) {
        if (!validData(user,password)) return;
    }

    @Override
    public boolean validData(String user, String psw) {
        boolean valid = true;

        if (user.isEmpty())
        {
            mView.showUserError();
            valid = false;
        }

        if (psw.isEmpty())
        {
            mView.showPasswordError();
            valid = false;
        }

        return valid;
    }

    @Override
    public void bindView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mView = null;
    }
}
