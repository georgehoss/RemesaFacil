package ve.com.phl.remesafacil.Requests;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import ve.com.phl.remesafacil.Model.Request;
import ve.com.phl.remesafacil.Model.Tasa;
import ve.com.phl.remesafacil.R;
import ve.com.phl.remesafacil.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment implements RequestContract.View {

    private RequestContract.Presenter mPresenter;
    private DatabaseReference mDbTasa;
    private DatabaseReference mDbRequest;
    private FirebaseUser mUser;
    private float mTasa=0;
    private boolean conver=false;
    @BindView(R.id.et_bsS) EditText mEtBss;
    @BindView(R.id.et_pcl) TextView mEtPcl;
    @BindView(R.id.et_account) EditText mEtAccountNumber;
    @BindView(R.id.et_nombre) EditText mEtAccountName;
    @BindView(R.id.et_observations) EditText mEtObservations;
    @BindView(R.id.et_id) EditText mEtAccountId;
    @BindView(R.id.tv_date) TextView mTvDate;
    @BindView(R.id.tv_tasa) TextView mTvTasa;
    @BindView(R.id.tv_recept) TextView mTvRecept;
    @BindView(R.id.tv_transf) TextView mTvTransf;
    @BindView(R.id.tv_simbol1) TextView mTvSimbol1;
    @BindView(R.id.tv_simbol2) TextView mTvSimbol2;

    public RequestFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.tv_cambio) void change(){
        conver = mPresenter.chooseConvert(conver);
    }

    @OnClick(R.id.bt_send) void sendInfo(){
        mPresenter.validData(mEtAccountNumber.getText().toString().trim(),
                mEtAccountName.getText().toString().trim(),
                mEtAccountId.getText().toString().trim(),
                mEtObservations.getText().toString().trim(),
                mEtBss.getText().toString().trim(),
                mEtPcl.getText().toString().trim());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        ButterKnife.bind(this,view);
        mDbTasa = FirebaseDatabase.getInstance().getReference(Tasa.DB_TASA);
        mDbRequest = FirebaseDatabase.getInstance().getReference(Request.DB_REQUEST);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mPresenter == null)
            mPresenter = new RequestPresenter(this);
        else
            mPresenter.bindView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        getTasa();
        setInputBs();
        getUserInfo();
    }

    @Override
    public void setBss(String bss) {
        mEtBss.setText(bss);
    }

    @Override
    public void setPcl(String pcl) {
        mEtPcl.setText(pcl);
    }

    @Override
    public void sendRequest(String account,String name,String id,String observations,String bss, String pcl){
        String mId = mDbRequest.push().getKey();
        if (mId!=null){
            Request request = new Request(mId,mUser.getDisplayName(),mUser.getEmail(),bss,pcl,account,name,id,observations,String.valueOf(mTasa), Utils.getDateString());
            mDbRequest.child(mId).setValue(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    showSuccessfullMsg();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    showUnsuccessfullMsg();
                }
            });
        }
    }

    @Override
    public void showConfirmDialog() {

    }

    @Override
    public void getTasa() {
        mTasa = 0;
        setTasa(String.valueOf(mTasa));
        mDbTasa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren())
                {
                    Tasa tasa = itemSnapshot.getValue(Tasa.class);
                    if (tasa!=null) {
                        setTasa(tasa.getValue());
                        setDate(tasa.getDate());
                        mTasa = mPresenter.setValue(tasa.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void setTasa(String tasa) {
        tasa = tasa.replace(".",",");
        tasa = tasa + " PCL";
        mTvTasa.setText(tasa);
    }

    @Override
    public void setDate(String date) {
        mTvDate.setText(date);
    }

    @Override
    public void showAccountError() {
        mEtAccountNumber.setError(getString(R.string.account_empty));
        mEtAccountNumber.requestFocus();
    }

    @Override
    public void showAccountInvalidError() {
        mEtAccountNumber.setError(getString(R.string.invalid_account));
        mEtAccountNumber.requestFocus();
    }

    @Override
    public void showNameError() {
        mEtAccountName.setError(getString(R.string.empty_name));
        mEtAccountName.requestFocus();
    }

    @Override
    public void showIdError() {
        mEtAccountId.setError(getString(R.string.id_empty));
        mEtAccountId.requestFocus();
    }

    @Override
    public void showObservationError() {
        mEtObservations.setError("Debe indicar el nombre del banco  y/o tipo de cuenta!");
        mEtObservations.requestFocus();
    }

    @Override
    public void setInputBs() {
        mEtBss.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = mEtBss.getText().toString();
                setPcl(mPresenter.convertToPcl(text,mTasa,conver));
            }
        });
    }


    @Override
    public void showBsEmptyError() {
        mEtBss.setError(getString(R.string.monto_empty));
        mEtBss.requestFocus();
    }

    @Override
    public void showPcEmptyError() {
        mEtPcl.setError(getString(R.string.monto_empty));
        mEtPcl.requestFocus();
    }

    @Override
    public void bsStoPcl() {
        mTvSimbol1.setText(getString(R.string.bss));
        mTvSimbol2.setText(getString(R.string.pcl));
        mTvRecept.setText(getString(R.string.monto_a_recibir));
        mTvTransf.setText(getString(R.string.monto_a_transferir));
        mEtBss.setText("");
        mEtPcl.setText("");
    }

    @Override
    public void PcltoBsS() {
        mTvSimbol1.setText(getString(R.string.pcl));
        mTvSimbol2.setText(getString(R.string.bss));
        mTvRecept.setText(getString(R.string.monto_a_transferir));
        mTvTransf.setText(getString(R.string.monto_a_recibir));
        mEtBss.setText("");
        mEtPcl.setText("");
    }


    @Override
    public void bindPresenter(RequestContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getUserInfo() {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser == null) {
            MainActivity mainActivity = (MainActivity) getActivity();
            if (mainActivity!=null)
            mainActivity.signOut();
        }
    }

    @Override
    public void showSuccessfullMsg() {
        Toast.makeText(getActivity(), "Solicitud de Pago envíada con éxito!", Toast.LENGTH_LONG).show();
        if (getActivity()!=null)
        getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void showUnsuccessfullMsg() {
        Toast.makeText(getActivity(), "En estos momentos es posible procesar la solicitud, verifica la conexión e intente de nuevo.", Toast.LENGTH_LONG).show();
    }
}
