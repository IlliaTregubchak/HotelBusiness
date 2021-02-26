package ua.com.company.hotels.business.dto.rooms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchRoomDTO {
    private int numberMin;
    private int numberMax;
    private int floor;
    private int squareMin;
    private int squareMax;
    private boolean status;
    private String category;
    private double priceLess;
    private double priceHigh;
}
