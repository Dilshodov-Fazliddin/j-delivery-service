package uzumtech.jdeliveryservice.utils;

import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;

public final class MessageBuilder {

    public static String updateStatusMessage(Long parcelId, ParcelStatus parcelStatus) {
        return "Hi, your parcel with id:" + parcelId + " is " + parcelStatus + "\n"
                + "Thank you for choosing our delivery system";
    }

    public static String parcelRegistrationMessage(String firstName, ParcelStatus parcelStatus, Long id, Double price, Double distance,String merchantName) {
        return "Hi " + firstName + ",\n"
                + "Parcel ID: " + id + "\n"
                + "Status: " + parcelStatus + "\n"
                + "Distance: " + distance + " km\n"
                + "Price: " + price + "\n"
                + "From: " + merchantName;
    }
}
