package com.example.smashing2;

import android.app.Activity;
import android.content.Intent;
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

public class JoinActivity extends AppCompatActivity {
    EditText id;
    EditText pass;
    EditText cpwd;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button btnJoin  = findViewById(R.id.join);
        btnJoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                id = findViewById(R.id.editJID);
                pass = findViewById(R.id.editJPW);
                cpwd = findViewById(R.id.editCPWD);

                String email = id.getText().toString().trim();
                String pwd = pass.getText().toString().trim();
                String jCPwd = cpwd.getText().toString();

                if (email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"id를 입력해 주세요.",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pwd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력해 주세요.",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (jCPwd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"비밀번호 확인을 입력해 주세요.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pwd.equals(jCPwd) ){
                    Toast.makeText(getApplicationContext(),"비밀번호와 비밀번호 확인이 같도록 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth = FirebaseAuth.getInstance();

                jCPwd = jCPwd.trim();

                // 회원가입
                mAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), Join2Activity.class);
                                        startActivity(intent);
                                        //finish();
                                } else {
                                    Toast.makeText(JoinActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });



                Intent it = new Intent();
                it.putExtra("id", email);
                setResult(Activity.RESULT_OK,it);
                //finish();
            }
        });

    }
}
