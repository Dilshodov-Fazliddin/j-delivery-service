package uzumtech.jdeliveryservice.utils;

import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;

public final class MessageBuilder {

    public static String updateStatusMessage(Long parcelId, ParcelStatus parcelStatus) {
        return "Hi, your parcel with id:" + parcelId + " is " + parcelStatus + "\n"
                + "Thank you for choosing our delivery system";
    }

    public static String ParcelRegistrationMessage(String firstName, ParcelStatus parcelStatus, Long id) {
     return "Hi,"  + firstName + " your parcel with id:" + id +"\n" + parcelStatus;
    }
}
