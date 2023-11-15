package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;
import com.example.to_do_app.databinding.ActivityLoginBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DBhelper databaseHepler;
    public static String UserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHepler = new DBhelper(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserEmail = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                if(UserEmail.equals("")||password.equals(""))
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCredentials =databaseHepler.checkEmailPassword(UserEmail,password);

                    if(checkCredentials==true){
                        Toast.makeText(LoginActivity.this, "Login Successfully"+UserEmail, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}