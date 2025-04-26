package com.example.gitae.models;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gitae.MainActivity;
import com.example.gitae.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private Button signUpBtn;
    private TextInputEditText usernameSignUp, passwordSignUp, nimPengguna, emailPengguna;
    private FirebaseAuth auth;
    private static final String TAG = "SignUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();

        signUpBtn = findViewById(R.id.signUpBtn);
        usernameSignUp = findViewById(R.id.usernameSignUp);
        emailPengguna = findViewById(R.id.emailPengguna);
        passwordSignUp = findViewById(R.id.passwordSingUp);
        nimPengguna = findViewById(R.id.nimPengguna);

        signUpBtn.setOnClickListener(view -> {
            String username = String.valueOf(usernameSignUp.getText()).trim();
            String email = String.valueOf(emailPengguna.getText()).trim();
            String password = String.valueOf(passwordSignUp.getText()).trim();
            String NIM = String.valueOf(nimPengguna.getText()).trim();

            if (TextUtils.isEmpty(username)) {
                usernameSignUp.setError("Please enter username");
                usernameSignUp.requestFocus();
            } else if (TextUtils.isEmpty(email)) {
                emailPengguna.setError("Please enter email");
                emailPengguna.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                passwordSignUp.setError("Please enter password");
                passwordSignUp.requestFocus();
            } else if (password.length() < 6) {
                passwordSignUp.setError("Password must be at least 6 characters");
                passwordSignUp.requestFocus();
            } else if (TextUtils.isEmpty(NIM)) {
                nimPengguna.setError("Please enter your NIM");
                nimPengguna.requestFocus();
            } else {
                registerUser(username, email, password, NIM);
            }
        });
    }

    private void registerUser(String username, String email, String password, String NIM) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser fUser = auth.getCurrentUser();
                        if (fUser != null) {
                            String uid = fUser.getUid();
                            UserDetails userDetails = new UserDetails(uid, username, email, password, NIM);

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                            reference.child(uid).setValue(userDetails)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            fUser.sendEmailVerification();
                                            Toast.makeText(SignUp.this, "Account created successfully! Please verify your email.", Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(SignUp.this, "Failed to save user data: " + task1.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            Log.e(TAG, "Database Error: ", task1.getException());
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Sign Up Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, "SignUp Error: ", task.getException());
                    }
                });
    }
}
