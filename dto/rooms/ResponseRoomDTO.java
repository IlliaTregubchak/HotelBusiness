package ua.com.company.hotels.business.dto.rooms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ua.com.company.hotels.business.model.Room;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseRoomDTO {
    private List<Room> rooms;
    private long count;
    // це робиться для того, щоб клієнт знав загальну кількість сторінок для відображення
}
