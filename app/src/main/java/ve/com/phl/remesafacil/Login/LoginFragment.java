package ve.com.phl.remesafacil.Login;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ve.com.phl.remesafacil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void showUserError() {

    }

    @Override
    public void showPasswordError() {

    }

    @Override
    public void showLoginError() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mPresenter == null)
            mPresenter = new LoginPresenter(this);
        else
            mPresenter.bindView(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void bindPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.bindView(this);
    }
}
