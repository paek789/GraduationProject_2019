package com.example.smashing2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class Menu5Fragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu5);
    }

    public void setContentView(int fragment_menu5) {
    }

    String photoLink;
    String email;
    String id;
    ImageView User;
    FirebaseAuth mAuth;
    String stUid;
    String stEmail;
    //String Tag = getClass().getSimpleName();
    private StorageReference mStorageRef;
    Bitmap bitmap;
    private DatabaseReference myRef;
    FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_menu5, container, false);
        View v = inflater.inflate(R.layout.fragment_menu5, container, false);

        myRef =  FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("email", MODE_PRIVATE);
        stUid = sharedPreferences.getString("uid","");
        stEmail = sharedPreferences.getString("email","");

        //로그아웃
//        TextView Out = v.findViewById(R.id.textOut);
//        Out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();
//                mAuth.getInstance().signOut();
//                getActivity().finish();
////                Intent intent = new Intent(getActivity(), LoginActivity.class);
////                startActivity(intent);
//            }
//        });

        Button btn = v.findViewById(R.id.textView13);
        TextView logout = v.findViewById(R.id.textLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                //Context context = getActivity();
                //SharedPreferences autoLogin = context.getSharedPreferences("setting", Context.MODE_PRIVATE);

                Context context = getActivity();
                SharedPreferences autoLogin = context.getSharedPreferences("setting",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = autoLogin.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getActivity(),"로그아웃",Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }

        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Menu5_1Fragment.class);
                startActivity(intent);
            }
        });

        //프로필 다운 소스(성공)
//                //FirebaseStorage 인스턴스를 생성
//                FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
//                // 위의 저장소를 참조하는 파일명으로 지정
//                StorageReference storageReference = firebaseStorage.getReference().child("users/"+ email);
//
//                //StorageReference에서 파일 다운로드 URL 가져옴
//                if (User == null) {
//                    Toast.makeText(getActivity(), "프로필을 설정해 주세요", Toast.LENGTH_SHORT).show();
//                } else {
//                    storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Uri> task) {
//                            if (task.isSuccessful()) {
//                                // Glide 이용하여 이미지뷰에 로딩
//                                Glide.with(getActivity())
//                                        .load(task.getResult())
//                                        .override(1024, 980)
//                                        .into(User);
//                            } else {
//                                // URL을 가져오지 못하면 토스트 메세지
//                                Toast.makeText(getActivity(), "프로필 다운로드 실패", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }

        //프로필 다운 소스
        myRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (User == null) {
                    Toast.makeText(getActivity(), "프로필을 설정해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    //FirebaseStorage 인스턴스를 생성
                    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
//                // 위의 저장소를 참조하는 파일명으로 지정
                    StorageReference storageReference = firebaseStorage.getReference().child("users/" + email);
                    storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                // Glide 이용하여 이미지뷰에 로딩
                                Glide.with(getActivity())
                                        .load(task.getResult())
                                        //.override(1024, 980)
                                        .into(User);
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        User = (ImageView)v.findViewById(R.id.User);
        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });
        return v;
    }

    //Firebase 프로필 이미지 업로드
    public void uploadImage() {
        //이메일 가져오기
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

        StorageReference mountainsRef = mStorageRef.child("users").child(email);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        final UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                //여기다 여기...
                Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        photoLink = uri.toString();
                        Log.d("url 이거다", photoLink);

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        id = user.getUid();

                        //Log.d("url 이거다", photoLink);

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("users").child(id);

                        Hashtable<String, String> profile = new Hashtable<String, String>();
                        profile.put("email", email);
                        profile.put("photo", photoLink);
                        profile.put("key", id);
                        myRef.setValue(profile);

//                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        String s = dataSnapshot.getValue().toString();
//                        Log.d("Profile", s);
//                        if (dataSnapshot != null) {
//                            Toast.makeText(getActivity(), "이미지 업로드 완료!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(getActivity(), "이미지 업로드 실패!", Toast.LENGTH_SHORT).show();
//                    }
//                });
                        myRef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                String ss = dataSnapshot.getValue().toString();
                                Log.d("Profile", ss);
                                if (dataSnapshot != null) {
                                    Toast.makeText(getActivity(), "이미지 업로드 완료!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });

            }
        });
    }

    //갤러리 권한
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri image = data.getData();
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), image);
            User.setImageBitmap(bitmap);
            uploadImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}