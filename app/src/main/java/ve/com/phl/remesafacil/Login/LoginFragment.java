package ve.com.phl.remesafacil.Login;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ve.com.phl.remesafacil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;
    private FirebaseAuth mAuth;
    @BindView(R.id.et_email) EditText mEtEmail;
    @BindView(R.id.et_psw) EditText mEtPsw;
    @BindView(R.id.bt_singIn) Button mBtLogin;

    public LoginFragment() {
        // Required empty public constructor
    }



    @OnClick(R.id.bt_singIn) void singIn()
    {
        mPresenter.login(mEtEmail.getText().toString().trim(),mEtPsw.getText().toString().trim());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment}
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void showUserError() {
        mEtEmail.setError("El correo no puede estar vacío");
        mEtEmail.requestFocus();
    }

    @Override
    public void showPasswordError() {
        mEtPsw.setError("La contraseña no puede estar vacía");
        mEtPsw.requestFocus();
    }

    @Override
    public void showLoginError() {
        Toast.makeText(getActivity(), "Correo no registrado o contraseña incorrecta, verifica los datos e intenta de nuevo", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmailError() {
        mEtEmail.setError("Debe introducir un correo válido");
        mEtEmail.requestFocus();
    }

    @Override
    public void showPswError() {
        mEtPsw.setError("La contraseña debe contener al menos 6 caracteres");
        mEtPsw.requestFocus();
    }

    @Override
    public void signIn(String user, String psw) {
        mBtLogin.setEnabled(false);
        mAuth.signInWithEmailAndPassword(user, psw)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressBar.setVisibility(View.GONE);
                        mBtLogin.setEnabled(true);

                        if (!task.isSuccessful()) {
                            showLoginError();
                        }
                    }
                });
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
