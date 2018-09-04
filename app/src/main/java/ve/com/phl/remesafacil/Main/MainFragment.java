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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ve.com.phl.remesafacil.MainActivity;
import ve.com.phl.remesafacil.Model.Tasa;
import ve.com.phl.remesafacil.Payments.PaymentsFragment;
import ve.com.phl.remesafacil.R;
import ve.com.phl.remesafacil.Requests.RequestFragment;
import ve.com.phl.remesafacil.Utils.Utils;


public class MainFragment extends Fragment implements MainContract.View {
    private MainActivity main;
    private MainContract.Presenter mPresenter;
    private DatabaseReference mDbTasa;
    private FirebaseUser mUser;
    @BindView(R.id.et_tasa) EditText mEtTasa;
    @BindView(R.id.tv_date) TextView mTvDate;
    @BindView(R.id.tv_user) TextView mTvUser;
    @BindView(R.id.tv_tasa) TextView mTvTasa;
    @BindView(R.id.tv_userTasa) TextView mTvUserTasa;
    @BindView(R.id.ll_tasa) LinearLayout mLlTasa;

    public MainFragment() {
        // Required empty public constructor
    }


    @OnClick(R.id.bt_requests) void request(){
        launchRequest();
    }

    @OnClick(R.id.bt_payments) void payment(){ launchPayments();}

    @OnClick(R.id.bt_signOut) void signOut(){
        main.signOut();
    }

    @OnClick(R.id.bt_save) void saveTasa(){
       saveTasa(mPresenter.validTasa(mEtTasa.getText().toString().trim()));
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        mDbTasa = FirebaseDatabase.getInstance().getReference(Tasa.DB_TASA);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        main = (MainActivity) getActivity();
        getUserInfo();
        mDbTasa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren())
                {
                    Tasa tasa = itemSnapshot.getValue(Tasa.class);
                    if (tasa!=null) {
                        setTasa(tasa.getValue());
                        setUserTasa(tasa.getUser());
                        setDate(tasa.getDate());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser != null) {
            for (UserInfo profile : mUser.getProviderData()) {
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

            mPresenter.verifyAdmin(mUser.getEmail());
        }
    }

    @Override
    public void setUserInfo(String name) {
        mTvUser.setText(name);
    }

    @Override
    public void setDate(String date) {
        mTvDate.setText(date);
    }

    @Override
    public void setTasa(String value) {
        value = value.replace(".",",");
        String valor = value +" PCL";
        mTvTasa.setText(valor);
    }

    @Override
    public void setUserTasa(String userTasa) {
        mTvUserTasa.setText(userTasa);
    }

    @Override
    public void saveTasa(String tasa) {
        Tasa mTasa = new Tasa(Tasa.ID,tasa,Utils.getDateString(),mUser.getEmail());
        mDbTasa.child(mTasa.getId()).setValue(mTasa);
    }

    @Override
    public void hideTasa() {
        mLlTasa.setVisibility(View.GONE);
    }

    @Override
    public void showTasa() {
        mLlTasa.setVisibility(View.VISIBLE);
    }

    @Override
    public void launchRequest() {
        if (getActivity()!=null)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new RequestFragment()).addToBackStack(null).commit();
    }

    @Override
    public void launchPayments() {
        if (getActivity()!=null)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PaymentsFragment()).addToBackStack(null).commit();
    }
}
