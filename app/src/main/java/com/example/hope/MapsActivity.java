package com.example.hope;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private double latitude;
    private double longitude;
    private Button logout;
    private ConstraintLayout medicalbutton;
    boolean t =false;
    private Button dismiss ;

    private ConstraintLayout fireb;
    private ConstraintLayout policeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        dismiss = findViewById(R.id.buttoncancel);
        fireb = findViewById(R.id.firebutton);
        policeb =findViewById(R.id.policebutton);

//        logout=findViewById(R.id.logout);


        medicalbutton = findViewById(R.id.medicalbuton);

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
                final PopupWindow popupWindow = new PopupWindow(popupView, 900 ,1000, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                // dismiss the popup window when touched


                LayoutInflater inflater1 = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView1= inflater1.inflate(R.layout.confirmation_popup,null);
                final PopupWindow popupWindow1 = new PopupWindow(popupView1,900,1000,true);

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

                        popupWindow1.showAtLocation(view,Gravity.CENTER,0,0);

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
                final PopupWindow popupWindow = new PopupWindow(popupView, 900 ,1000, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                // dismiss the popup window when touched


                LayoutInflater inflater1 = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView1= inflater1.inflate(R.layout.confirmation_popup,null);
                final PopupWindow popupWindow1 = new PopupWindow(popupView1,900,1000,true);

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

                        popupWindow1.showAtLocation(view,Gravity.CENTER,0,0);

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
                final PopupWindow popupWindow = new PopupWindow(popupView, 900 ,1000, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                // dismiss the popup window when touched


                LayoutInflater inflater1 = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView1= inflater1.inflate(R.layout.confirmation_popup,null);
                final PopupWindow popupWindow1 = new PopupWindow(popupView1,900,1000,true);

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

                        popupWindow1.showAtLocation(view,Gravity.CENTER,0,0);

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

//
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(MapsActivity.this,Login.class));
//
//            }
//        });
//

    }

    private void fetchLocation() {
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
                    latitude=currentLocation.getLatitude();
                    longitude=currentLocation.getLongitude();

                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + " " + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(MapsActivity.this::onMapReady);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


//        latitude=currentLocation.getLatitude();
//        longitude=currentLocation.getLongitude();
        if(latitude!=0 && longitude!=0) {
            LatLng latLng = new LatLng(latitude, longitude);
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Location");
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20f));
            googleMap.addMarker(markerOptions);
        }
    }

    void cancelPopup(View view){





        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.cancel_popup, null);

        // create the popup window
        int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        popupWindow.getContentView().findViewById(R.id.buttondismiss2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
//                medicalbutton.setVisibility(View.VISIBLE);
//                fireb.setVisibility(View.VISIBLE);
//                policeb.setVisibility(View.VISIBLE);

                medicalbutton.setVisibility(View.VISIBLE);
                fireb.setVisibility(View.VISIBLE);
                policeb.setVisibility(View.VISIBLE);



                popupWindow.dismiss();



            }
        });




        // dismiss the popup window when touched



    }
    void confirmPopup(View view){





        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.confirmation_popup, null);

        // create the popup window
        int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        popupWindow.getContentView().findViewById(R.id.buttondismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                medicalbutton.setVisibility(View.VISIBLE);
                fireb.setVisibility(View.VISIBLE);
                policeb.setVisibility(View.VISIBLE);



                popupWindow.dismiss();



            }
        });




    }
}