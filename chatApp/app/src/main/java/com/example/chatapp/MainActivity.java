package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        Button signIn = findViewById(R.id.signInButton);
        EditText emailInput = (EditText) findViewById(R.id.emailInput);
        EditText PassInput = findViewById(R.id.passwordInput);
        PassInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        PassInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        Button signUp = findViewById(R.id.goToSignUp);

        FirebaseFirestore infos = FirebaseFirestore.getInstance();


        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email_in = emailInput.getText().toString();
                String pass_in = PassInput.getText().toString();

                CollectionReference usersCollection = infos.collection("Users");

                usersCollection.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    QuerySnapshot querySnapshot = task.getResult();
                                    if (querySnapshot != null) {
                                        List<DocumentSnapshot> documents = querySnapshot.getDocuments();

                                        // Iterate through the documents
                                        for (DocumentSnapshot document : documents) {
                                            Map<String, Object> data = document.getData();
                                            if((data.get("email").equals(email_in))&&(data.get("password").equals(pass_in))){
                                                showToast("Enter Successfull");
                                                Intent intent = new Intent(MainActivity.this, chat_ui.class);
                                                startActivity(intent);
                                            }

                                        }
                                        showToast("Wrong password or email");

                                    }
                                }
                            }
                        });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showToast("This is a short Toast message");
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivity(intent);
            }
        });


    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}