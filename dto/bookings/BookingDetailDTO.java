package ua.com.company.hotels.business.dto.bookings;

import lombok.Getter;
import lombok.Setter;
import ua.com.company.hotels.business.dto.guests.GuestDTO;
import ua.com.company.hotels.business.model.Room;
import ua.com.company.hotels.business.util.DateParser;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDetailDTO {
    private long id;
    private long statusId;
    private String checkInDate;
    private String checkOutDate;
    private String createdAt;
    private String comment;
    private GuestDTO guest;
    private Room room;

//    @JsonIgnore
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public BookingDetailDTO(LocalDateTime checkInDate, LocalDateTime checkOutDate, LocalDateTime createdAt) {
        this.checkInDate = DateParser.parseFromDate(checkInDate);
        this.checkOutDate = DateParser.parseFromDate(checkOutDate);
        this.createdAt =  DateParser.parseFromDate(createdAt);
    }
}
