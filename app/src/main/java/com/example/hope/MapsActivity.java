package com.example.hope;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private double latitude;
    private double longitude;
    private Button logout;
    private ConstraintLayout medicalbutton;
    boolean t = false;
    private Button dismiss;

    private ConstraintLayout fireb;
    private ConstraintLayout policeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        dismiss = findViewById(R.id.buttoncancel);
        fireb = findViewById(R.id.firebutton);
        policeb = findViewById(R.id.policebutton);

//        logout=findViewById(R.id.logout);


        medicalbutton = findViewById(R.id.medicalbuton);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation(new LatLng(0, 0));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        findViewById(R.id.refreshlocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchLocation(new LatLng(0, 0));
            }
        });
        fireb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                medicalbutton.setVisibility(View.INVISIBLE);
                fireb.setVisibility(View.INVISIBLE);
                policeb.setVisibility(View.INVISIBLE);


                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup, null);

                // create the popup window
                int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, 900, 1000, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                // dismiss the popup window when touched


                LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView1 = inflater1.inflate(R.layout.confirmation_popup, null);
                final PopupWindow popupWindow1 = new PopupWindow(popupView1, 900, 1000, true);

//

                popupWindow.getContentView().findViewById(R.id.buttoncancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        medicalbutton.setVisibility(View.VISIBLE);
                        fireb.setVisibility(View.VISIBLE);
                        policeb.setVisibility(View.VISIBLE);
                        popupWindow.dismiss();


                    }
                });


                popupWindow.getContentView().findViewById(R.id.buttonconfirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        popupWindow.dismiss();

                        popupWindow1.showAtLocation(view, Gravity.CENTER, 0, 0);

                        popupWindow1.getContentView().findViewById(R.id.buttondismiss).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow1.dismiss();

                                medicalbutton.setVisibility(View.VISIBLE);
                                fireb.setVisibility(View.VISIBLE);
                                policeb.setVisibility(View.VISIBLE);
                            }
                        });


                    }
                });

            }
        });


//
//

        policeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                medicalbutton.setVisibility(View.INVISIBLE);
                fireb.setVisibility(View.INVISIBLE);
                policeb.setVisibility(View.INVISIBLE);


                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup, null);

                // create the popup window
                int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, 900, 1000, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                // dismiss the popup window when touched


                LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView1 = inflater1.inflate(R.layout.confirmation_popup, null);
                final PopupWindow popupWindow1 = new PopupWindow(popupView1, 900, 1000, true);

//

                popupWindow.getContentView().findViewById(R.id.buttoncancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        medicalbutton.setVisibility(View.VISIBLE);
                        fireb.setVisibility(View.VISIBLE);
                        policeb.setVisibility(View.VISIBLE);
                        popupWindow.dismiss();


                    }
                });


                popupWindow.getContentView().findViewById(R.id.buttonconfirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        popupWindow.dismiss();

                        popupWindow1.showAtLocation(view, Gravity.CENTER, 0, 0);

                        popupWindow1.getContentView().findViewById(R.id.buttondismiss).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow1.dismiss();

                                medicalbutton.setVisibility(View.VISIBLE);
                                fireb.setVisibility(View.VISIBLE);
                                policeb.setVisibility(View.VISIBLE);
                            }
                        });


                    }
                });

            }
        });


        medicalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                medicalbutton.setVisibility(View.INVISIBLE);
                fireb.setVisibility(View.INVISIBLE);
                policeb.setVisibility(View.INVISIBLE);


                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup, null);

                // create the popup window
                int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, 900, 1000, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                // dismiss the popup window when touched


                LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView1 = inflater1.inflate(R.layout.confirmation_popup, null);
                final PopupWindow popupWindow1 = new PopupWindow(popupView1, 900, 1000, true);

//

                popupWindow.getContentView().findViewById(R.id.buttoncancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        medicalbutton.setVisibility(View.VISIBLE);
                        fireb.setVisibility(View.VISIBLE);
                        policeb.setVisibility(View.VISIBLE);
                        popupWindow.dismiss();


                    }
                });


                popupWindow.getContentView().findViewById(R.id.buttonconfirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String m = "jiji";


                        popupWindow.dismiss();

                        popupWindow1.showAtLocation(view, Gravity.CENTER, 0, 0);
//                        if(m!=""){
//                            m="jiii";
//                        }


                        TextView t = popupWindow1.getContentView().findViewById(R.id.confirmtext);
                        String id = "1";
                        FirebaseFirestore db = FirebaseFirestore.getInstance();

                        DocumentReference result = db.collection("ambulances").document(id);
                        result.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String driver, driver_no;
                                DocumentSnapshot reference;
                                Log.d("tagisthis", "message");
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    reference = task.getResult();
                                    if (document.exists()) {

                                        driver = Objects.requireNonNull(document.get("driver_name")).toString();
                                        driver_no = (String) document.get("driver_number");


                                        t.setText(driver);


                                    } else {
                                        Log.d("TAG", "No such document");
                                    }
                                } else {
                                    Log.d("TAG", "get failed with ", task.getException());
                                }
                            }
                        });


                        popupWindow1.getContentView().findViewById(R.id.buttondismiss).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow1.dismiss();


                                medicalbutton.setVisibility(View.VISIBLE);
                                fireb.setVisibility(View.VISIBLE);
                                policeb.setVisibility(View.VISIBLE);
                            }
                        });


                    }
                });
//                        popupView.setOnTouchListener(new View.OnTouchListener() {
//                            @Override
//                            public boolean onTouch(View v, MotionEvent event) {
//                                popupWindow.dismiss();
//                                return true;
//                            }
//                        });

            }
        });

        findViewById(R.id.emercontactbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+"7708107382"));



                if (ActivityCompat.checkSelfPermission(MapsActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);
                    return;
                }
                startActivity(i);

            }
        });

    }

    private void fetchLocation(LatLng l) {
        if (l.latitude == 0 && l.longitude == 0) {
            if (ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

                return;
            }
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            ((Task) task).addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        currentLocation = location;
                        latitude = currentLocation.getLatitude();
                        longitude = currentLocation.getLongitude();

                        Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + " " + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                        assert supportMapFragment != null;
                        supportMapFragment.getMapAsync(MapsActivity.this::onMapReady);
                    }
                }
            });
        } else {

            longitude = l.latitude;
            longitude = l.longitude;

            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            assert supportMapFragment != null;
            supportMapFragment.getMapAsync(MapsActivity.this::onMapReady);

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


//        latitude=currentLocation.getLatitude();
//        longitude=currentLocation.getLongitude();
        if (latitude != 0 && longitude != 0) {
            LatLng latLng = new LatLng(latitude, longitude);
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Location");
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20f));
            googleMap.addMarker(markerOptions);
        }
    }






}
