package ve.com.phl.remesafacil.Register;


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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ve.com.phl.remesafacil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements RegisterContract.View {
    private RegisterContract.Presenter mPresenter;
    private FirebaseAuth mAuth;
    @BindView(R.id.et_firstName) EditText mEtFirstName;
    @BindView(R.id.et_lastName) EditText mEtLastName;
    @BindView(R.id.et_number) EditText mEtNumber;
    @BindView(R.id.et_email) EditText mEtEmail;
    @BindView(R.id.et_psw) EditText mEtPsw;
    @BindView(R.id.bt_signUp) Button mBtSignUp;


    @OnClick(R.id.bt_signUp) void signUp(){
        mPresenter.register(mEtFirstName.getText().toString().trim(),
                mEtLastName.getText().toString().trim(),
                mEtNumber.getText().toString(),
                mEtEmail.getText().toString().trim(),
                mEtPsw.getText().toString().trim());
    }


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mPresenter == null)
            mPresenter = new RegisterPresenter(this);
        else
            mPresenter.bindView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void showFirstNameError() {
        mEtFirstName.setError(getString(R.string.nombre_vacio));
        mEtFirstName.requestFocus();
    }

    @Override
    public void showLastNameError() {
        mEtLastName.setError(getString(R.string.apellido_vacio));
        mEtLastName.requestFocus();
    }

    @Override
    public void showNumberError() {
        mEtNumber.setError(getString(R.string.numero_vacio));
        mEtNumber.requestFocus();
    }

    @Override
    public void showEmailError() {
        mEtEmail.setError(getString(R.string.correo_vacio));
        mEtEmail.requestFocus();
    }

    @Override
    public void showEmailFormatError() {
        mEtEmail.setError(getString(R.string.correo_invalido));
        mEtEmail.requestFocus();
    }

    @Override
    public void showPswLengthError() {
        mEtPsw.setError(getString(R.string.contrasena_corta));
        mEtPsw.requestFocus();
    }

    @Override
    public void showPasswordError() {
        mEtPsw.setError(getString(R.string.contrasena_vacia));
        mEtPsw.requestFocus();
    }

    @Override
    public void signUpUser(final String firstName, final String lastName, String number, String email, String password) {
        mBtSignUp.setEnabled(false);
        if (getActivity()!=null)
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        mBtSignUp.setEnabled(true);
                        if (!task.isSuccessful()) {

                            if (task.getException()!=null && task.getException().getMessage()!=null && task.getException().getMessage().contains("email address is already in use"))
                                showEmailDuplicatedError();
                            else
                                showSingUpFailedError();
                        }
                        else {
                            updateUserInfo(firstName+" "+lastName);
                        }

                    }
                });
    }

    @Override
    public void showEmailDuplicatedError() {
        Toast.makeText(getActivity(), getString(R.string.error_correo_en_uso), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSingUpFailedError() {
        Toast.makeText(getActivity(), getString(R.string.error_crear_cuenta), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void updateUserInfo(String name) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        if (user!=null)
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("UpdateUser", "User profile updated.");
                        }
                    }
                });
    }

    @Override
    public void bindPresenter(RegisterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
