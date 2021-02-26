package ua.com.company.hotels.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.company.hotels.business.dto.rooms.ResponseRoomDTO;
import ua.com.company.hotels.business.dto.rooms.SaveRoomDTO;
import ua.com.company.hotels.business.dto.rooms.SearchRoomDTO;
import ua.com.company.hotels.business.dto.rooms.UpdateRoomDTO;
import ua.com.company.hotels.business.model.Room;
import ua.com.company.hotels.business.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> save(@RequestBody SaveRoomDTO saveRoomDTO) {
        final Room saveRoom = roomService.save(saveRoomDTO);
        return new ResponseEntity<>(saveRoom, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Room> update(@RequestBody UpdateRoomDTO updateRoomDTO, @RequestParam Long id) {
        final Room updateRoom = roomService.update(updateRoomDTO, id);
        return new ResponseEntity<>(updateRoom, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseRoomDTO> findAll(@RequestParam int page) {
        final ResponseRoomDTO rooms = roomService.findAll(page);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Room>> search(@RequestBody SearchRoomDTO searchRoomDTO, @RequestParam int page) {
        final List<Room> searchRoom = roomService.search(searchRoomDTO, page);
        return new ResponseEntity<>(searchRoom, HttpStatus.OK);
    }
}
