package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class signUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Button signIn = findViewById(R.id.goToSignIp);
        Button signUp = findViewById(R.id.signUpButton);
        EditText email1 =(EditText) findViewById(R.id.emailInput);
        EditText name1 =(EditText) findViewById(R.id.nameInput);
        EditText pass1 =(EditText) findViewById(R.id.passwordInput);
        pass1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        EditText comfPass1 =(EditText) findViewById(R.id.comfirmPasswordInput);
        comfPass1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        comfPass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        FirebaseFirestore infos = FirebaseFirestore.getInstance();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionReference usersCollection = infos.collection("Users");        //refrence to the "Users" collection in firebase

                usersCollection.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {   //done from fetching data from databsse
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                int id = 0;
                                if (task.isSuccessful()) {      //successful fetching
                                    QuerySnapshot usersNow = task.getResult();      //contains the documents created in the collection "Users"
                                    if (usersNow != null) {
                                        int numberOfDocuments = usersNow.size();   //how many documents(users) we have
                                        id = numberOfDocuments+1;
                                        createUser(id);
                                    } else {    //we don't have users before already created
                                        id = 1;
                                        createUser(id);
                                    }
                                }
                            }
                        });


            signIn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(signUp.this, MainActivity.class);
                    startActivity(intent);
                }
            });


}

public void createUser(int id){
    String email = email1.getText().toString();
    String name = name1.getText().toString();
    String pass = pass1.getText().toString();
    String comfPass = comfPass1.getText().toString();

    if(name.equals("")){
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(pass.length()>=8){

                if(pass.equals(comfPass)) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("id", id);
                    data.put("name", name);
                    data.put("email", email);
                    data.put("password", pass);

                    DocumentReference docRef = infos.collection("Users").document(Integer.toString(id));    //document refrence of the id passed in argument, the document will not be already created, it will be created automaticly

                    docRef.set(data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {       //if data set successfully
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent intent = new Intent(signUp.this, chat_ui.class);
                                    startActivity(intent);
                                    Toast.makeText(signUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(signUp.this, "Error occured please try again", Toast.LENGTH_LONG).show();
                                }
                            });
                }
                else{
                    Toast.makeText(signUp.this, "Password not match", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(signUp.this, "Enter at least 8 characters password", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(signUp.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
        }
    }
    else{
        Toast.makeText(signUp.this, "Enter your name", Toast.LENGTH_SHORT).show();
    }
}
        });


    }

}
