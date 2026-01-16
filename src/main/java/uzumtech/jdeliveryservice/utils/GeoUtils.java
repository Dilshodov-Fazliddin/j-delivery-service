package uzumtech.jdeliveryservice.utils;

public final class GeoUtils {

    private static final double EARTH_RADIUS_KM = 6371;

    private GeoUtils() {}

    public static double distanceInKm(
            double latitudeFrom, double longitudeFrom,
            double latitudeTo, double longitudeTo
    ) {
        double dLat = Math.toRadians(latitudeTo - latitudeFrom);
        double dLon = Math.toRadians(longitudeTo - longitudeFrom);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(latitudeFrom))
                * Math.cos(Math.toRadians(latitudeTo))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

}
