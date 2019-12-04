package com.example.hamroride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverLoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login, register;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);


        email = findViewById(R.id.etemaildriver);
        password = findViewById(R.id.etpassworddriver);

        login = findViewById(R.id.driverlogin);
        register = findViewById(R.id.driverregister);

        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(DriverLoginActivity.this, "I am clicked", Toast.LENGTH_SHORT).show();
                final String etemail = email.getText().toString();
                final String etpassword = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(etemail, etpassword).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(DriverLoginActivity.this, "Registration error", Toast.LENGTH_SHORT).show();

                        } else {
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(user_id).child("name");
                            current_user_db.setValue(etemail);
                            Toast.makeText(DriverLoginActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String etemail = email.getText().toString();
                final String etpassword = password.getText().toString();
                mAuth.signInWithEmailAndPassword(etemail,etpassword).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if (!task.isSuccessful()){
                       Toast.makeText(DriverLoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(DriverLoginActivity.this, "Login Success" , Toast.LENGTH_SHORT).show();
                       Intent intent=new Intent(DriverLoginActivity.this,DriverMapActivity.class);
                       startActivity(intent);
                   }
                    }
                });
            }
        });
    }



//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(authStateListener);
//    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}
