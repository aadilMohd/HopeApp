package com.example.hope;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class AmbulanceMessage {

    TextView t;

    public String Message,driver,driver_no;
    DocumentSnapshot reference ;
    AmbulanceMessage(TextView t1){

        this.t =t1;


    }



    public void displayMessage(String Driver,String DriverNm){


        Message=Driver+" is booked and the number is"+DriverNm;

    }

    public String message(){
        return Message;

    }




    public void getMes(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ArrayList<String> ids = new ArrayList<>();

        DataVariables m = new DataVariables();
        db.collection("ambulances")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ids.add(document.getId());
                            }
                        } else {
                            Log.w("error","Error getting documents.", task.getException());
                        }
                    }
                });


        String id = "1";
        DocumentReference result= db.collection("ambulances").document(id);
        result.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Log.d("tagisthis","message");
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    reference=task.getResult();
                    if (document.exists()) {

                        driver = Objects.requireNonNull(document.get("driver_name")).toString();
                        driver_no=(String) document.get("driver_number");




                        t.setText(driver);



                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });




//        Log.d("msg",);

//        Log.d("docc",reference.toString());

        Log.d("doc", "DocumentSnapshot data: " +  m.getMes()+" is booked and the number is"+driver_no);




    }






}
