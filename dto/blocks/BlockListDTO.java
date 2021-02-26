package ua.com.company.hotels.business.dto.blocks;

import lombok.Getter;
import lombok.Setter;
import ua.com.company.hotels.business.util.DateParser;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlockListDTO {
    // FIND ALL
    private long id;
    private String name;
    private String note;
    // LocalDateTime дуже великий обєкт, тому ми викор String, шоб повернути дату в гарному форматі
    private String createdAt;

    // створюємо formatter і робимо йому @JsonIgnore, шоб він не полетів у Postman
//    @JsonIgnore
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public BlockListDTO(long id, String name, String note, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.note = note;
        // автоматичне переведення LocalDateTime y String за допомогою formatterа
        this.createdAt = DateParser.parseFromDate(createdAt);
    }
}
