package com.pack.app_design;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextNombre, editTextEmail, editTextFono, editTextEdad, editTextContrasenia;

    Button buttomregistrar;

    FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextFono = findViewById(R.id.editTextFono);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextContrasenia = findViewById(R.id.editTextContrasenia);
        buttomregistrar = findViewById(R.id.buttomRegistro);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(RegisterActivity.this);

        buttomregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String pass = editTextContrasenia.getText().toString();
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextEmail.setError("Correo invalido");
                    editTextEmail.setFocusable(true);
                } else if (pass.length()<4) {
                    editTextContrasenia.setError("Contraseña invalida");
                    editTextContrasenia.setFocusable(true);
                }else {
                    registrarUser(email,pass);
                }
            }
        });

    }

    private void registrarUser(String email, String pass) {
        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor");
        progressDialog.setCancelable(false);
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            assert user != null;
                            String uid = user.getUid();
                            String email = editTextEmail.getText().toString();
                            String nombre = editTextNombre.getText().toString();
                            String fono = editTextFono.getText().toString();
                            String edad = editTextEdad.getText().toString();
                            String pass = editTextContrasenia.getText().toString();

                            HashMap<Object,String> DatoUsuario = new HashMap<>();

                            DatoUsuario.put("uid", uid);
                            DatoUsuario.put("correo", email);
                            DatoUsuario.put("pass", pass);
                            DatoUsuario.put("nombre", nombre);
                            DatoUsuario.put("fono", fono);
                            DatoUsuario.put("edad", edad);
                            DatoUsuario.put("imagen","");

                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            DatabaseReference reference = database.getReference("Users");

                            reference.child(uid).setValue(DatoUsuario);

                            Toast.makeText(RegisterActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}