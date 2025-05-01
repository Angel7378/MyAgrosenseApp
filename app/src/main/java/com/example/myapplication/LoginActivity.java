package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText usernameEditText, passwordEditText;
    private TextView forgotPasswordLink, createAccountLink;
    private ImageView googleLogin, facebookLogin, twitterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize all views
        loginButton = findViewById(R.id.loginBtn);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        forgotPasswordLink = findViewById(R.id.forgotPasswordLink);
        createAccountLink = findViewById(R.id.createAccountLink);
        googleLogin = findViewById(R.id.googleLogin);
        facebookLogin = findViewById(R.id.facebookLogin);
        twitterLogin = findViewById(R.id.twitterLogin);

        // LOGIN BUTTON: Validate and Navigate
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (validateLogin(username, password)) {
                    Intent intent = new Intent(LoginActivity.this, ThingSpeakDataActivity.class);
                    startActivity(intent);
                    finish(); // optional: to prevent coming back to login screen
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // FORGOT PASSWORD: Navigate to ForgotPasswordActivity
        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        // CREATE ACCOUNT: Navigate to SignUpActivity
        createAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        // SOCIAL LOGINS (Placeholders)
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with Google Sign-In logic
                Toast.makeText(LoginActivity.this, "Redirecting to Google Sign-In...", Toast.LENGTH_SHORT).show();
                // TODO: startActivity(new Intent(LoginActivity.this, GoogleSignInActivity.class));
            }
        });

        facebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with Facebook SDK login logic
                Toast.makeText(LoginActivity.this, "Redirecting to Facebook Sign-In...", Toast.LENGTH_SHORT).show();
            }
        });

        twitterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with Twitter login logic
                Toast.makeText(LoginActivity.this, "Redirecting to Twitter Sign-In...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Dummy validation method
    private boolean validateLogin(String username, String password) {
        return username.equals("ANGEL") && password.equals("EDI@PRG"); // Replace with real logic
    }
}
