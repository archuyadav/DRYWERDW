package com.example.drywerdw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText editEmail;
    private EditText editPass;
    private ProgressDialog statusDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Bundle bundle = getIntent().getExtras();
        Button btnLogin = findViewById(R.id.btnLogin);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        TextView textForgot = findViewById(R.id.textForgot);
        final EditText editPass=findViewById(R.id.editPass);


//code to connect to firebase authentication.
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // btn event listeners

        btnLogin.setOnClickListener(this);
        textForgot.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        hideDialog(statusDialog);
        if (currentUser != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
            // link to home
        } else {
            // error msg
        }
    }

    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btnLogin:
                processLogin();
                break;
            case R.id.textForgot:
                gotoForgot();
                break;
        }
    }

    private void gotoForgot() {
     startActivity(new Intent(this,ForgotActivity.class));
    }

    private void processLogin() {
        String email = editEmail.getText().toString();
        String password = editPass.getText().toString();
        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(editEmail, "email or password cannot be empty", Snackbar.LENGTH_INDEFINITE).show();
        } else if (email.length() < 10 || password.length() < 8) {
            Snackbar.make(editEmail, "email or password length invalid", Snackbar.LENGTH_INDEFINITE).show();
        } else {
            statusDialog = showDialog("wait for signin");

        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    updateUI(null);
                }
            }
        });
    }


    public ProgressDialog showDialog(String msg){
        Context context;
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle(msg);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }
    public void hideDialog(ProgressDialog dialog){
        if(dialog!=null){
            if(dialog.isShowing()){
                dialog.dismiss();
            }
        }
    }


}
