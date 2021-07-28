package com.example.porsche;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;

    private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private String mVerificationId;



    private static final String TAG = "LOGIN_TAG";

    private FirebaseAuth firebaseAuth;

    //process dialog
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);

        TextView resendCodeTv = findViewById(R.id.resendCodeTv);

        EditText phoneEt = findViewById(R.id.phoneEt);

        TextView codeSentDescription = findViewById(R.id.codeSentDescription);

//        binding.phoneLl.setVisibility(View.VISIBLE);
//        binding.codeLl.setVisibility(View.GONE);
        LinearLayout phoneLl = findViewById(R.id.phoneLl);
        LinearLayout codeLl = findViewById(R.id.codeLl);
        phoneLl.setVisibility(View.VISIBLE);
        codeLl.setVisibility(View.GONE);

        Button phoneContinueBtn = findViewById(R.id.phoneContinueBtn);
        firebaseAuth = FirebaseAuth.getInstance();

        //init progress dialog
        pd = new ProgressDialog(this);
        pd.setTitle("Please wait...");
        pd.setCanceledOnTouchOutside(false);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull @org.jetbrains.annotations.NotNull PhoneAuthCredential phoneAuthCredential) {

                signInWithPhoneAuthCredential(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                pd.dismiss();

                Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onCodeSent(@NonNull @NotNull String verificationId, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationId, forceResendingToken);
                Log.d(TAG, "onCodeSent: "+verificationId);

                mVerificationId = verificationId;

                forceResendingToken = token;
                pd.dismiss();

//                binding.phoneLl.setVisibility(View.GONE);
//                binding.codeLl.setVisibility(View.VISIBLE);
                phoneLl.setVisibility(View.GONE);
                codeLl.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), "Verification code sent...", Toast.LENGTH_SHORT).show();
                codeSentDescription.setText("Please enter the verification code we sent to \n "+phoneEt.getText().toString().trim());
            }
        };

        phoneContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String phone = phoneEt.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Please enter a phone number", Toast.LENGTH_SHORT).show();
                } else {
//                    startPhoneNumberVerification(phone);
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }

            }
        });


        resendCodeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = phoneEt.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Please enter a phone number", Toast.LENGTH_SHORT).show();
                } else {
                    resendVerificationCode(phone, forceResendingToken);
                }

            }
        });

        Button codeSubmitBtn = findViewById(R.id.codeSubmitBtn);
        EditText codeEt = findViewById(R.id.codeEt);

        codeSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = codeEt.getText().toString().trim();
                if(TextUtils.isEmpty(code)){
                    Toast.makeText(getApplicationContext(), "Please enter the code we sent you.", Toast.LENGTH_SHORT).show();
                }
                else{
                    verifyPhonenumberWithCode(mVerificationId,code);
                }
            }
        });
    }




    private void startPhoneNumberVerification(String phone) {
        pd.setMessage("Verifying phone number");
        pd.show();

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void resendVerificationCode(String phone, PhoneAuthProvider.ForceResendingToken token) {
        pd.setMessage("Resending Verification Coder");
        pd.show();

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setForceResendingToken(token)
                .setCallbacks(mCallbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyPhonenumberWithCode(String verificationId, String code) {
        pd.setMessage("Verifying code");
        pd.show();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        pd.setMessage("Logging you in ....");
        pd.show();

        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        pd.dismiss(); //logged in successfully

                        String phone = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getPhoneNumber();
                        Toast.makeText(getApplicationContext(), "Logged in as "+phone, Toast.LENGTH_SHORT).show();

//                        starting dashboard activity //todo
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}