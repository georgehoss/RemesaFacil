package ve.com.phl.remesafacil.Main;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ve.com.phl.remesafacil.MainActivity;
import ve.com.phl.remesafacil.R;
import ve.com.phl.remesafacil.Utils.Utils;


public class MainFragment extends Fragment implements MainContract.View {
    private MainActivity main;
    private MainContract.Presenter mPresenter;
    @BindView(R.id.et_tasa) EditText mEtTasa;
    @BindView(R.id.tv_date) TextView mTvDate;
    @BindView(R.id.tv_user) TextView mTvUser;

    public MainFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.bt_signOut) void signOut(){
        main.signOut();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        main = (MainActivity) getActivity();
        getUserInfo();
        setDate(Utils.getDateString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mPresenter == null)
            mPresenter = new MainPresenter(this);
        else
            mPresenter.bindView(this);
    }

    @Override
    public void bindPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getUserInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();

                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();

                if (mPresenter.validName(name))
                    setUserInfo(name);



            };
        }
    }

    @Override
    public void setUserInfo(String name) {
        String msg = "Bienvenido(a): "+name;
        mTvUser.setText(msg);
    }

    @Override
    public void setDate(String date) {
        mTvDate.setText(date);
    }
}
