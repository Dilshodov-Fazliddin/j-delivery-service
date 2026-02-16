package uzumtech.jdeliveryservice.utils;

public final class GeoUtils {

    private static final double EARTH_RADIUS_KM = 6371.0088;

    private static final double ROAD_COEFFICIENT = 1.2;

    private GeoUtils() {}

    public static double distanceKm(
            double latitudeFrom, double longitudeFrom,
            double latitudeTo, double longitudeTo
    ) {

        double dLat = Math.toRadians(latitudeTo - latitudeFrom);
        double dLon = Math.toRadians(longitudeTo - longitudeFrom);

        double lat1Rad = Math.toRadians(latitudeFrom);
        double lat2Rad = Math.toRadians(latitudeTo);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1Rad)
                * Math.cos(lat2Rad)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
    public static double distanceKmByRoad(
            double latitudeFrom, double longitudeFrom,
            double latitudeTo, double longitudeTo
    ) {
        return distanceKm(latitudeFrom, longitudeFrom, latitudeTo, longitudeTo)
                * ROAD_COEFFICIENT;
    }

}