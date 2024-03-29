package com.example.appmusicplayer;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailedit,passedit;
    private Button btnregis;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();

        emailedit=findViewById(R.id.email);
        passedit=findViewById(R.id.password);
        btnregis=findViewById(R.id.btnregis);

        passedit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        btnregis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    private void register(){
        String email,pass;
        email=emailedit.getText().toString();
        pass=passedit.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui long nhap email!!!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui long nhap password!!!",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Taọ tài khoản thành công",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Taọ tài khoản không thành công",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}
