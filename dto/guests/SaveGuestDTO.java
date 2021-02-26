package ua.com.company.hotels.business.dto.guests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveGuestDTO {
    private String name;
    private String nationality;
    private String birthDate;
    private int phoneNumber;
}
