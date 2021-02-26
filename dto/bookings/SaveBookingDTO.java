package ua.com.company.hotels.business.dto.bookings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveBookingDTO {
    private long guestId;
    private long roomId;
    private String checkInDate;
    private String checkOutDate;
    private String comment;
}
