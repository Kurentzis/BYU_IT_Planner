package com.example.BYU_IT_Planner.ui.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.BYU_IT_Planner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Settings extends Fragment {

    SignInButton signInButton;
    GoogleSignInClient mgoogleSignInClient;
    TextView result;
    Button signOut;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, null);

        signInButton = view.findViewById(R.id.google_signIn_bt);
        signOut = view.findViewById(R.id.google_signOut_bt);
        result = view.findViewById(R.id.resultSignIn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mgoogleSignInClient = GoogleSignIn.getClient(this.requireContext(), gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = mgoogleSignInClient.getSignInIntent();
                startActivityForResult(signIn, 100);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mgoogleSignInClient.signOut();

            }
        });



        return view;
    }









    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            googleSignInOk(account);
        }
        catch (Exception e){
            googleSignInOk(null);
        }
    }

    public void googleSignInOk(GoogleSignInAccount account)
    {
        if(account == null){
            result.setText("Something went wrong!");
        }
        else{
            result.setText(account.getDisplayName());

        }
    }
}