package com.techdevsubhopriyo.toolbox;

import java.lang.Math;

public class Distance{
    public static double distance(
            double latitudeFrom,
            double longitudeFrom,
            double latitudeTo,
            double longitudeTo,
            double earthRadius)
    {
        latitudeFrom=Math.toRadians(latitudeFrom);
        longitudeFrom=Math.toRadians(longitudeFrom);
        latitudeTo=Math.toRadians(latitudeTo);
        longitudeTo=Math.toRadians(longitudeTo);

        double longDelta=longitudeTo-longitudeFrom;
        double a,b,angle;
        a=Math.pow(Math.cos(latitudeTo)*Math.sin(longDelta),2)+
                Math.pow(Math.cos(latitudeFrom)*Math.sin(latitudeTo)-
                        Math.sin(latitudeFrom)*Math.cos(latitudeTo)*Math.cos(longDelta),2);
        b=Math.sin(latitudeFrom)*Math.sin(latitudeTo)+Math.cos(latitudeFrom)*Math.cos(latitudeTo)*Math.cos(longDelta);
        angle=Math.atan2(Math.sqrt(a),b);
        return angle*earthRadius;
    }
}