package com.example.harsh.loginactivity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity extends AppCompatActivity {
    EditText PeditText;
    EditText Eedittext;
    CheckBox checkBox;
    ProgressDialog progressDialogue;
    FirebaseAuth firebaseAuth;
    public void register(View view){
        String email=Eedittext.getText().toString().trim();
        String password=PeditText.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
             return;
        }
        progressDialogue.setMessage("Registering...");
        progressDialogue.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialogue.dismiss();
               if(task.isSuccessful())
                Toast.makeText(MainActivity.this, "You have been registered", Toast.LENGTH_SHORT).show();
            else

                Toast.makeText(MainActivity.this,"Registeration failed!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            relativeLayout1.setVisibility(View.VISIBLE);
            relativeLayout2.setVisibility(View.VISIBLE);
        }
    };
    RelativeLayout relativeLayout1,relativeLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         PeditText=findViewById(R.id.passEt);
         firebaseAuth=FirebaseAuth.getInstance();
         progressDialogue=new ProgressDialog(this);
         Eedittext=findViewById(R.id.userEt);
         relativeLayout1=findViewById(R.id.relLayout1);
         relativeLayout2=findViewById(R.id.relLayout2);
         handler.postDelayed(runnable,2000);
         checkBox=findViewById(R.id.checkbox);
         checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                   PeditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else{

                        PeditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
             }
         });
    }
}
