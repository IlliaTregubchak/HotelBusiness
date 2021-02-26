package ua.com.company.hotels.business.dto.guests;

import lombok.Getter;
import lombok.Setter;
import ua.com.company.hotels.business.util.DateParser;

import java.time.LocalDateTime;

@Getter
@Setter
// Різниця між GuestDTO i Guest(model) в тому, що в GuestDTO String createdAt, String birthDate;
public class GuestDTO {
    private long id;
    private String name;
    private String birthDate;
    private String nationality;
    private int phoneNumber;
    private String createdAt;

//    @JsonIgnore
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    // УВАГА!!! LocalDateTime birthDate, LocalDateTime createdAt бо так в БД!!!!!!!
    public GuestDTO(long id, String name, LocalDateTime birthDate, String nationality, int phoneNumber, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.birthDate = DateParser.parseFromDate(birthDate);
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.createdAt = DateParser.parseFromDate(createdAt);
    }
}
