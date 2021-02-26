package ua.com.company.hotels.business.dto.rooms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveRoomDTO {
    private int number;
    private int floor;
    private int square;
    private String category;
    private double price;
    private boolean smoking;
    private boolean status;
}
