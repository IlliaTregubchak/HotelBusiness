package ua.com.company.hotels.business.dto.blocks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditBlockDTO {
    // SAVE, UPDATE
    private String name;
    private String note;
    // data проставляєится автоматично (якщо зберішаємо)
}
