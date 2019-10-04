package com.vikaspatelp83.apps.villageprogrammer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.vikaspatelp83.apps.villageprogrammer.LoginActivity;

public class SignUpActivity extends AppCompatActivity {
    public Button signup_1;
    public TextView alert;
    public EditText getSEmail,getSPass,getSPassConf;
    public FirebaseAuth mfirebaseAuth;
    private FirebaseAuth mAuth;
// ...


    private String emaili,passi,passic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_ui);
        mfirebaseAuth = FirebaseAuth.getInstance();


        signup_1 = findViewById(R.id.signup_ok);
        alert = findViewById(R.id.password_in_alert);
        getSPass = findViewById(R.id.password_in);
        getSPassConf = findViewById(R.id.password_in_cnf);
        getSEmail = findViewById(R.id.emailid_in);

        SignUpActivity.buttonEffect(signup_1);
        signup_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpActivity.addClickEffect(signup_1);

                emaili = getSEmail.getText().toString();
                passi = getSPass.getText().toString();
                passic = getSPassConf.getText().toString();
                startRegister();
                //if(passi.equals(passic)){
                //  alert.setVisibility(View.INVISIBLE);
              /*  try {
                    if (TextUtils.isEmpty(emaili) || TextUtils.isEmpty(passi)) {
                        Toast.makeText(SignUpActivity.this, "Fields are Empty ", Toast.LENGTH_SHORT).show();
                    } else
                        mfirebaseAuth.createUserWithEmailAndPassword(emaili, passi).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "You are registered", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                }
                            }
                        });
                    //}
                }
                catch (Exception e){
                    Log.d(String.valueOf(29), "onClick: "+e.toString());
                }
                    //Toast.makeText(SignUpActivity.this,"Registration Successfull",Toast.LENGTH_SHORT).show();

                }
               /* else {
                    alert.setVisibility(View.VISIBLE);
                }*/
            }

        });
    } // end oncreate
    void startRegister(){
        if(TextUtils.isEmpty(emaili) || TextUtils.isEmpty(passi) || !TextUtils.equals(passi,passic)) {
            alert.setVisibility(View.VISIBLE);

            Toast.makeText(SignUpActivity.this,"Fields are Empty ",Toast.LENGTH_SHORT).show();
        }
        else{
            alert.setVisibility(View.INVISIBLE);
            Toast.makeText(SignUpActivity.this, "Registering you", Toast.LENGTH_LONG).show();
            mfirebaseAuth.createUserWithEmailAndPassword(emaili, passi).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "You are registered", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                    }
                }
            });
        }

    }

    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe04FDEC8, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
    public static void addClickEffect(View view)
    {
        Drawable drawableNormal = view.getBackground();

        Drawable drawablePressed = view.getBackground().getConstantState().newDrawable();
        drawablePressed.mutate();
        drawablePressed.setColorFilter(Color.argb(20, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);

        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[] {android.R.attr.state_pressed}, drawablePressed);
        listDrawable.addState(new int[] {}, drawableNormal);
        view.setBackground(listDrawable);
    }
}
