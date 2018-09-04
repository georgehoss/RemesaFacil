package ve.com.phl.remesafacil.Payments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ve.com.phl.remesafacil.MainActivity;
import ve.com.phl.remesafacil.Model.Request;
import ve.com.phl.remesafacil.R;


public class PaymentsFragment extends Fragment implements PaymentsContract.View, RequestAdapter.OnClick {

    private PaymentsContract.Presenter mPresenter;
    private DatabaseReference mDbRequest;
    private RequestAdapter mAdapter;
    private FirebaseUser mUser;
    private ArrayList<Request> mRequests;
    @BindView(R.id.rv_payments) RecyclerView mRvPayments;

    public PaymentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payments, container, false);
        ButterKnife.bind(this,view);
        mRequests = new ArrayList<>();
        mRvPayments.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDbRequest = FirebaseDatabase.getInstance().getReference(Request.DB_REQUEST);
        mAdapter = new RequestAdapter(mRequests,getContext(),this);
        mRvPayments.setAdapter(mAdapter);
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getUserInfo();
        getRequest();
    }

    @Override
    public void bindPresenter(PaymentsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mPresenter == null)
            mPresenter = new PaymentsPresenter(this);
        else
            mPresenter.bindView(this);
    }

    @Override
    public void getRequest() {
        mDbRequest.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mRequests = new ArrayList<>();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren())
                {
                    Request request = itemSnapshot.getValue(Request.class);
                    if (request!=null && request.getUserEmail().equals(mUser.getEmail())) {
                        mRequests.add(request);
                    }
                }

                mAdapter.setRequests(mRequests);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
    public void onDeleteRequest(Request request) {

    }
}
