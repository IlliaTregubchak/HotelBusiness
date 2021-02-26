package ua.com.company.hotels.business.service;

import ua.com.company.hotels.business.dto.rooms.ResponseRoomDTO;
import ua.com.company.hotels.business.dto.rooms.SaveRoomDTO;
import ua.com.company.hotels.business.dto.rooms.SearchRoomDTO;
import ua.com.company.hotels.business.dto.rooms.UpdateRoomDTO;
import ua.com.company.hotels.business.model.Room;

import java.util.List;

public interface RoomService {

    Room save(SaveRoomDTO saveRoomDTO);

    Room update(UpdateRoomDTO updateRoomDTO, Long id);

    ResponseRoomDTO findAll(int page);

    List<Room> search(SearchRoomDTO searchRoomDTO, int page);
}
