package ve.com.phl.remesafacil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ve.com.phl.remesafacil.Main.MainContract;
import ve.com.phl.remesafacil.Main.MainFragment;
import ve.com.phl.remesafacil.Splash.SplashFragment;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("es");
        mAuth.useAppLanguage();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    clearBackStack();
                    launchMain();

                } else {
                    // User is signed out
                    clearBackStack();
                    launchSplash();
                }
            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();
        mUser = mAuth.getCurrentUser();
        if (mUser==null)
            launchSplash();
        else {
            launchMain();
        }

        mAuth.addAuthStateListener(mAuthListener);

    }

    private void launchSplash(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SplashFragment()).commit();
    }

    private void launchMain(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signOut() {
        mAuth.signOut();
    }

    private void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }
}
