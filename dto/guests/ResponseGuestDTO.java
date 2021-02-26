package ua.com.company.hotels.business.dto.guests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseGuestDTO {
     private List<GuestDTO> guests;
     private long count;
}
