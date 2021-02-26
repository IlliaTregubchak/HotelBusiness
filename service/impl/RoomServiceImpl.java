package ua.com.company.hotels.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.com.company.hotels.business.dto.rooms.ResponseRoomDTO;
import ua.com.company.hotels.business.dto.rooms.SaveRoomDTO;
import ua.com.company.hotels.business.dto.rooms.SearchRoomDTO;
import ua.com.company.hotels.business.dto.rooms.UpdateRoomDTO;
import ua.com.company.hotels.business.model.Room;
import ua.com.company.hotels.business.repository.RoomRepository;
import ua.com.company.hotels.business.service.RoomService;
import ua.com.company.hotels.business.util.Constants;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room save(SaveRoomDTO saveRoomDTO) {
        Assert.notNull(saveRoomDTO, "Room is null");
        final Room room = new Room();
        room.setNumber(saveRoomDTO.getNumber());
        room.setCategory(saveRoomDTO.getCategory());
        room.setFloor(saveRoomDTO.getFloor());
        room.setPrice(saveRoomDTO.getPrice());
        room.setSmoking(saveRoomDTO.isSmoking());
        room.setSquare(saveRoomDTO.getSquare());
        return roomRepository.save(room);
    }

    @Override
    public Room update(UpdateRoomDTO updateRoomDTO, Long id) {
        Assert.notNull(updateRoomDTO, "Room is null");
        Assert.notNull(id, "Id is null");
        final Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Room not found"));
        room.setPrice(updateRoomDTO.getPrice());
        room.setStatus(updateRoomDTO.isStatus());
        // повертати модель простіше, ніж DTO!!!
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public ResponseRoomDTO findAll(int page) {
        // якщо це плагінація, то Repository повертає page, тому ми викликаємо getContent(),
        // щоб повернути List
        final List<Room> rooms = roomRepository.findAll(PageRequest.of(page, Constants.ENTITY_LIMIT)).getContent();
        final long count = roomRepository.count();
        return new ResponseRoomDTO(rooms, count);
    }

    @Override
    public List<Room> search(SearchRoomDTO searchRoomDTO, int page) {
        Assert.notNull(searchRoomDTO, "Room is null");
        System.out.println(searchRoomDTO.toString());
        return roomRepository.search(searchRoomDTO.getPriceLess(),
                searchRoomDTO.getPriceHigh(),
                searchRoomDTO.isStatus(),
                searchRoomDTO.getCategory(),
                searchRoomDTO.getNumberMin(),
                searchRoomDTO.getNumberMax(),
                searchRoomDTO.getFloor(),
                searchRoomDTO.getSquareMin(),
                searchRoomDTO.getSquareMax());
    }

    // where price >= 10 and price <= 12 and square >= 100 and square <= :squareTo and ;
}
