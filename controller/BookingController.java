package ua.com.company.hotels.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.company.hotels.business.dto.bookings.*;
import ua.com.company.hotels.business.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> save(@RequestBody SaveBookingDTO saveBookingDTO) {
        final BookingDTO saveBooking = bookingService.save(saveBookingDTO);
        return new ResponseEntity<>(saveBooking, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateReturnBookingDTO> update(@RequestBody UpdateBookingDTO updateBookingDTO, @RequestParam long id) {
        final UpdateReturnBookingDTO updatedBooking = bookingService.update(updateBookingDTO, id);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    @PutMapping("/updateBookingRoom")
    public ResponseEntity<Boolean> updateBookingRoom(@RequestParam long roomId, @RequestParam long id) {
        final boolean isTrue = bookingService.updateBookingRoom(roomId, id);
        return new ResponseEntity(isTrue, HttpStatus.OK);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Boolean> updateStatus(@RequestParam long statusId, @RequestParam long id) {
        final boolean status = bookingService.updateStatus(statusId, id);
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseBookingDTO> findAll(@RequestParam int page) {
        final ResponseBookingDTO bookings = bookingService.findAll(page);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<BookingDetailDTO> findById(@RequestParam long id) {
        final BookingDetailDTO bookingById = bookingService.findById(id);
        return new ResponseEntity<>(bookingById, HttpStatus.OK);
    }
}
