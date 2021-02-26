package ua.com.company.hotels.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.company.hotels.business.dto.guests.GuestDTO;
import ua.com.company.hotels.business.dto.guests.ResponseGuestDTO;
import ua.com.company.hotels.business.dto.guests.SaveGuestDTO;
import ua.com.company.hotels.business.dto.guests.UpdateGuestDTO;
import ua.com.company.hotels.business.service.GuestService;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestDTO> save(@RequestBody SaveGuestDTO saveGuestDTO) {
        final GuestDTO saveGuest = guestService.save(saveGuestDTO);
        return new ResponseEntity<>(saveGuest, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GuestDTO> update(@RequestBody UpdateGuestDTO updateGuestDTO, @RequestParam Long id) {
        final GuestDTO updateGuest = guestService.update(updateGuestDTO, id);
        return new ResponseEntity<>(updateGuest, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseGuestDTO> findAll(@RequestParam int page) {
        final ResponseGuestDTO guests = guestService.findAll(page);
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity<ResponseGuestDTO> findByName(@RequestParam String name, @RequestParam int page) {
        final ResponseGuestDTO guestsByName = guestService.findByName(name, page);
        return new ResponseEntity<>(guestsByName, HttpStatus.OK);
    }
}
