package ua.com.company.hotels.business.dto.bookings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.company.hotels.business.util.DateParser;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {
    private long id;
    private long statusId;
    private String checkInDate;
    private String checkOutDate;
    private String createdAt;

//    @JsonIgnore
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    // String змінюємо на LocalDateTime вручну!!!
    public BookingDTO(long id, long statusId, LocalDateTime checkInDate, LocalDateTime checkOutDate, LocalDateTime createdAt) {
        this.id = id;
        this.statusId = statusId;
        this.checkInDate = DateParser.parseFromDate(checkInDate);
        this.checkOutDate = DateParser.parseFromDate(checkOutDate);
        this.createdAt = DateParser.parseFromDate(createdAt);
    }
}