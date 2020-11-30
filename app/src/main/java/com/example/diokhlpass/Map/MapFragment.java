package com.example.diokhlpass.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.diokhlpass.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

         //Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.google_maps);

        //Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

                          LatLng boMar, zig, kaolack, stl,mbour, th,dl;
                          MarkerOptions markerOptions1 = new MarkerOptions();
                          MarkerOptions markerOptions2 = new MarkerOptions();
                          MarkerOptions markerOptions3 = new MarkerOptions();
                          MarkerOptions markerOptions4 = new MarkerOptions();
                          MarkerOptions markerOptions5 = new MarkerOptions();
                          MarkerOptions markerOptions6 = new MarkerOptions();
                          MarkerOptions markerOptions7 = new MarkerOptions();


                         boMar =  new LatLng(14.741599208885612, -17.402187488088256);
                         zig  =  new LatLng(12.584300586368231, -16.261376013494036);
                         kaolack  =  new LatLng(14.132188064485058, -16.070243074600906);
                         stl  =  new LatLng(15.989055109545099, -16.488511132249858);
                         mbour =  new LatLng(14.426239615980627, -16.972103303433034);
                         th =  new LatLng(14.779576119359678, -16.945104961099773);
                         dl =  new LatLng(14.650726289793353, -16.25413294575985);

                        markerOptions1.position(boMar).title("Gare des Baux Maraîchers");
                        markerOptions2.position(zig).title("Gare routiére de Ziguenchor");
                        markerOptions3.position(kaolack).title("Gare routiére de Kaolack");
                        markerOptions4.position(stl).title("Gare routiére de Saint-Louis");
                        markerOptions5.position(mbour).title("Gare routiére de Mbour");
                        markerOptions6.position(th).title("Gare routiére de Thiès");
                        markerOptions7.position(dl).title("Gare routiére de Diourbel");


                    /*    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(boMar,10));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(zig,10));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(kaolack,10));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(stl,10));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mbour,10));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(th,10));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dl,10));*/

                        googleMap.addMarker(markerOptions1);
                        googleMap.addMarker(markerOptions2);
                        googleMap.addMarker(markerOptions3);
                        googleMap.addMarker(markerOptions4);
                        googleMap.addMarker(markerOptions5);
                        googleMap.addMarker(markerOptions6);
                        googleMap.addMarker(markerOptions7);

            }
        });
        //Return view
        return view;
    }

}
