package ua.com.company.hotels.business.dto.bookings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.company.hotels.business.util.DateParser;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdateBookingDTO {
    private String checkInDate;
    private String checkOutDate;
    private String comment;

//    @JsonIgnore
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public UpdateBookingDTO(LocalDateTime checkInDate, LocalDateTime checkOutDate, String comment) {
        this.checkInDate = DateParser.parseFromDate(checkInDate);
        this.checkOutDate = DateParser.parseFromDate(checkOutDate);
        this.comment = comment;
    }
}
