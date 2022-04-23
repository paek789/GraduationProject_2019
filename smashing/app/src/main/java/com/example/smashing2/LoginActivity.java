package com.example.smashing2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText editID;
    EditText editPWD;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editID = findViewById(R.id.editID);
        editPWD = findViewById(R.id.editPW);

        mAuth = FirebaseAuth.getInstance();

        SharedPreferences autoLogin = getSharedPreferences("setting", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = autoLogin.edit();

        if(autoLogin.getString("id","").length() > 0 && autoLogin.getString("password","").length() > 0) {
            editID.setText(autoLogin.getString("id",""));
            editPWD.setText(autoLogin.getString("password",""));
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }

        //로그인
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent it1 = new Intent(getApplicationContext(),MainActivity.class);

                final String LogID = editID.getText().toString();
                LogID.trim();
                final String LogPWD = editPWD.getText().toString();
                LogPWD.trim();

                if (LogID.isEmpty() && LogPWD.isEmpty()){
                    Toast.makeText(getApplicationContext(), "ID와 PassWord를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(LogID.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ID를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (LogPWD.isEmpty()){
                    Toast.makeText(getApplicationContext(), "PassWord를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(LogID, LogPWD)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("id", LogID);
                                    startActivity(intent);
                                    editor.putString("id", LogID);
                                    editor.putString("password",LogPWD);
                                    editor.commit();
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        //회원가입
        Button btnJoin = findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,JoinActivity.class);
                startActivityForResult(intent, 101);
                finish();
            }
        });
    }
}
