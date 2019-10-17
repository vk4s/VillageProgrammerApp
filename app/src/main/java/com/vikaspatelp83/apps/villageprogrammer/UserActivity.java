package com.vikaspatelp83.apps.villageprogrammer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {
    Button verify;
    TextView verifyMsg;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = mAuth.getCurrentUser();
        verify = findViewById(R.id.verify);
        verifyMsg = findViewById(R.id.verify_message);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Verification Email Sent to " + user.getEmail().toString();

                verifyMsg.setText(msg);
                user.sendEmailVerification()
                        .addOnCompleteListener(this, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                // Re-enable button
                                findViewById(R.id.verify).setEnabled(true);

                                if (task.isSuccessful()) {
                                    Toast.makeText(UserActivity.this,
                                            "Verification email sent to " + user.getEmail(),
                                            Toast.LENGTH_SHORT).show();
                                    verifyMsg.setText("Email Verified");
                                } else {
                                    Log.e(TAG, "sendEmailVerification", task.getException());
                                    Toast.makeText(UserActivity.this,
                                            "Failed to send verification email.",
                                            Toast.LENGTH_SHORT).show();
                                    verifyMsg.setText("Email Verification Failed");
                                }
                            }
                        });

            }

        });

        setContentView(R.layout.activity_user);
    }//end oncreate
}//end class