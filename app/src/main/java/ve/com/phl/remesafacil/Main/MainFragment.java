package ve.com.phl.remesafacil.Main;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ve.com.phl.remesafacil.MainActivity;
import ve.com.phl.remesafacil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements MainContract.View {
    private MainActivity main;

    public MainFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.bt_signOut) void signOut(){
        main.signOut();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
    }

    @Override
    public void bindPresenter(MainContract.Presenter presenter) {

    }

}
