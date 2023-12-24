package com.example.quickbook;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText editText = (EditText) findViewById(R.id.editTextText);
        EditText editText2 = (EditText) findViewById(R.id.editTextText2);
        Button buttonn = (Button) findViewById(R.id.registerbutton);
        ProgressBar pbar = (ProgressBar) findViewById(R.id.pbar);
        TextView textView2 = (TextView) findViewById(R.id.signuptextView2);
        mAuth = FirebaseAuth.getInstance();

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });
        buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbar.setVisibility(View.VISIBLE);
                String email,pass;
                email = editText.getText().toString();
                pass = editText2.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(register.this, "Enter email", Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(register.this, "Enter password", Toast.LENGTH_SHORT).show();

                }
                mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@ NonNull  Task<AuthResult> task) {
                                pbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(register.this, "Account created ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    pbar.setVisibility(View.GONE);
                                    Toast.makeText(register.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}