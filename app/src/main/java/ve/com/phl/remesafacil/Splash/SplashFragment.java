package ve.com.phl.remesafacil.Splash;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ve.com.phl.remesafacil.Login.LoginFragment;
import ve.com.phl.remesafacil.R;
import ve.com.phl.remesafacil.Register.RegisterFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment implements SplashContract.View {


    public SplashFragment() {
        // Required empty public constructor
    }


    @OnClick(R.id.bt_signUp) void singUp(){
        launchSingUp();
    }

    @OnClick(R.id.bt_singIn) void singIn(){
        launchSingIn();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this,view);
        return  view;
    }

    @Override
    public void launchSingUp() {
        if (getActivity()!=null)
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new RegisterFragment()).addToBackStack(null).commit();
    }

    @Override
    public void launchSingIn() {
        if (getActivity()!=null)
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).addToBackStack(null).commit();
    }

    @Override
    public void bindPresenter(SplashContract.Presenter presenter) {

    }
}
