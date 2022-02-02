package com.techdevsubhopriyo.toolbox;

import com.google.android.gms.maps.model.LatLng;

public class GeoTool {
    private double latitudeFrom, longitudeFrom, latitudeTo, longitudeTo, earthRadius;

    /**
     * Default constructor
    * */
    public GeoTool() {
        latitudeFrom = 0.0;
        latitudeTo = 0.0;
        longitudeFrom = 0.0;
        longitudeTo = 0.0;
        earthRadius = 6371.0;

    }

    /** Pass Latlng object to find distance between 2 location objects */
    public GeoTool(LatLng location1, LatLng location2) {
        latitudeFrom = location1.latitude;
        latitudeTo = location2.latitude;
        longitudeFrom = location1.longitude;
        longitudeTo = location2.longitude;
        earthRadius = 6371.0;
    }

    public GeoTool(LatLng location1, LatLng location2, double earthRadius) {
        latitudeFrom = location1.latitude;
        latitudeTo = location2.latitude;
        longitudeFrom = location1.longitude;
        longitudeTo = location2.longitude;
        this.earthRadius = earthRadius;
    }

    public GeoTool(double latitudeFrom, double longitudeFrom, double latitudeTo, double longitudeTo) {
        this.latitudeFrom = latitudeFrom;
        this.latitudeTo = latitudeTo;
        this.longitudeFrom = longitudeFrom;
        this.longitudeTo = longitudeTo;
        earthRadius = 6371.0;
    }

    public GeoTool(double latitudeFrom, double longitudeFrom, double latitudeTo, double longitudeTo, double earthRadius) {
        this.latitudeFrom = latitudeFrom;
        this.latitudeTo = latitudeTo;
        this.longitudeFrom = longitudeFrom;
        this.longitudeTo = longitudeTo;
        this.earthRadius = earthRadius;
    }

    public String getDefaultUnit() {
        return "kilometer";
    }

    public void setEarthRadius(double earthRadius) {
        this.earthRadius = earthRadius;
    }

    public double getEarthRadius() {
        return this.earthRadius;
    }

    public double getDistance() {
        latitudeFrom = Math.toRadians(latitudeFrom);
        longitudeFrom = Math.toRadians(longitudeFrom);
        latitudeTo = Math.toRadians(latitudeTo);
        longitudeTo = Math.toRadians(longitudeTo);

        double longDelta = longitudeTo - longitudeFrom;
        double a, b, angle;
        a = Math.pow(Math.cos(latitudeTo) * Math.sin(longDelta), 2) +
                Math.pow(Math.cos(latitudeFrom) * Math.sin(latitudeTo) -
                        Math.sin(latitudeFrom) * Math.cos(latitudeTo) * Math.cos(longDelta), 2);
        b = Math.sin(latitudeFrom) * Math.sin(latitudeTo) + Math.cos(latitudeFrom) * Math.cos(latitudeTo) * Math.cos(longDelta);
        angle = Math.atan2(Math.sqrt(a), b);
        return angle * earthRadius;
    }

    public double getLatitudeDifference() {
        return Math.abs(latitudeFrom - latitudeTo);
    }

    public double getLongitudeDifference() {
        return Math.abs(longitudeFrom - longitudeTo);
    }
}