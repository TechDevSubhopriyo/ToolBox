package com.techdevsubhopriyo.toolbox_ktx

import com.google.android.gms.maps.model.LatLng
import kotlin.math.*


class GeoTool {
    private var latitudeFrom: Double
    private var longitudeFrom: Double
    private var latitudeTo: Double
    private var longitudeTo: Double
    private var earthRadius: Double

    constructor() {
        latitudeFrom = 0.0
        latitudeTo = 0.0
        longitudeFrom = 0.0
        longitudeTo = 0.0
        earthRadius = 6371.0
    }

    constructor(location1: LatLng, location2: LatLng) {
        latitudeFrom = location1.latitude
        latitudeTo = location2.latitude
        longitudeFrom = location1.longitude
        longitudeTo = location2.longitude
        earthRadius = 6371.0
    }

    constructor(location1: LatLng, location2: LatLng, earthRadius: Double) {
        latitudeFrom = location1.latitude
        latitudeTo = location2.latitude
        longitudeFrom = location1.longitude
        longitudeTo = location2.longitude
        this.earthRadius = earthRadius
    }

    constructor(
        latitudeFrom: Double,
        longitudeFrom: Double,
        latitudeTo: Double,
        longitudeTo: Double
    ) {
        this.latitudeFrom = latitudeFrom
        this.latitudeTo = latitudeTo
        this.longitudeFrom = longitudeFrom
        this.longitudeTo = longitudeTo
        earthRadius = 6371.0
    }

    constructor(
        latitudeFrom: Double,
        longitudeFrom: Double,
        latitudeTo: Double,
        longitudeTo: Double,
        earthRadius: Double
    ) {
        this.latitudeFrom = latitudeFrom
        this.latitudeTo = latitudeTo
        this.longitudeFrom = longitudeFrom
        this.longitudeTo = longitudeTo
        this.earthRadius = earthRadius
    }

    var EarthRadius: Double
        get() = earthRadius
        set(value) {earthRadius=value}
    val defaultUnit: String
        get() = "Kilometer"
    val distance: Double
        get() {
            latitudeFrom = Math.toRadians(latitudeFrom)
            longitudeFrom = Math.toRadians(longitudeFrom)
            latitudeTo = Math.toRadians(latitudeTo)
            longitudeTo = Math.toRadians(longitudeTo)
            val longDelta = longitudeTo - longitudeFrom
            val angle: Double
            val a: Double = (cos(latitudeTo) * sin(longDelta)).pow(2.0) +
                    (cos(latitudeFrom) * sin(latitudeTo) -
                            sin(latitudeFrom) * cos(latitudeTo) * cos(longDelta)).pow(2.0)
            val b: Double = sin(latitudeFrom) * sin(latitudeTo) + cos(latitudeFrom) * cos(
                latitudeTo
            ) * cos(longDelta)
            angle = atan2(sqrt(a), b)
            return angle * earthRadius
        }
    val latitudeDifference: Double
        get() = abs(latitudeFrom - latitudeTo)
    val longitudeDifference: Double
        get() = abs(longitudeFrom - longitudeTo)
}