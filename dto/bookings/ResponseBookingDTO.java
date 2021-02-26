package ua.com.company.hotels.business.dto.bookings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseBookingDTO {
    private List<BookingDTO> bookings;
    private long count;
}

