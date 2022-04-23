package com.example.smashing2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Join2Activity extends AppCompatActivity {

    //태그 선언
    private static final String TAG = "Join2Activity";

    EditText name;
    EditText birth1;
    EditText birth2;
    EditText birth3;
    EditText phone1;
    EditText phone2;
    EditText phone3;
    EditText add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);
        getIntent();
        name = findViewById(R.id.editName);
        birth1 = findViewById(R.id.editBirth1);
        birth2 = findViewById(R.id.editBirth2);
        birth3 = findViewById(R.id.editBirth3);
        phone1 = findViewById(R.id.editPhone1);
        phone2 = findViewById(R.id.editPhone2);
        phone3 = findViewById(R.id.editPhone3);
        add = findViewById(R.id.add);

        //버튼 액티비티
        Button btnJoin = findViewById(R.id.join);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME = name.getText().toString().trim();
                String BIRTH1 = birth1.getText().toString().trim();
                String BIRTH2 = birth2.getText().toString().trim();
                String BIRTH3 = birth3.getText().toString().trim();
                String PHONE1 = phone1.getText().toString().trim();
                String PHONE2 = phone2.getText().toString().trim();
                String PHONE3 = phone3.getText().toString().trim();
                String ADD = add.getText().toString().trim();

                if (NAME.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (BIRTH1.isEmpty() || BIRTH2.isEmpty() || BIRTH3.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "생년월일을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (PHONE1.isEmpty() || PHONE2.isEmpty() || PHONE3.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "전화번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (ADD.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "주소를 입력하세요! ", Toast.LENGTH_SHORT).show();
                } else
                    profileUpdate();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //회원 정보 등록 메소드
    private void profileUpdate() {
        String Name = ((EditText)findViewById(R.id.editName)).getText().toString();
        String Birth1 = ((EditText)findViewById(R.id.editBirth1)).getText().toString();
        String Birth2 = ((EditText)findViewById(R.id.editBirth2)).getText().toString();
        String Birth3 = ((EditText)findViewById(R.id.editBirth3)).getText().toString();
        String Phone1 = ((EditText)findViewById(R.id.editPhone1)).getText().toString();
        String Phone2 = ((EditText)findViewById(R.id.editPhone2)).getText().toString();
        String Phone3 = ((EditText)findViewById(R.id.editPhone3)).getText().toString();
        String Add = ((EditText)findViewById(R.id.add)).getText().toString();

        if (Name.length() > 2 && Birth1.length() > 3 && Birth2.length() > 1 && Birth3.length() > 1
        && Phone1.length() > 2 && Phone2.length() > 3 && Phone3.length() > 3 && Add.length() > 3){

            //파이어 베이스 유저 초기화
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            //파이어 베이스 DB초기화
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            MemberInfo memberInfo = new MemberInfo(Name, Birth1, Birth2, Birth3, Phone1, Phone2, Phone3, Add);
            db.collection("users").document(user.getUid()).set(memberInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "회원정보(DB) 등록 완료! ", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "회원정보(DB) 등록 실패! ", Toast.LENGTH_SHORT).show();
                            Log.w(TAG,"Error", e);
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "회원정보를 입력하세요! ", Toast.LENGTH_SHORT).show();
        }


    }

}
