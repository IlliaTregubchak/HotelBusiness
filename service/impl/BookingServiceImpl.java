package ua.com.company.hotels.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.com.company.hotels.business.dto.bookings.*;
import ua.com.company.hotels.business.dto.guests.GuestDTO;
import ua.com.company.hotels.business.model.Booking;
import ua.com.company.hotels.business.model.Room;
import ua.com.company.hotels.business.model.Status;
import ua.com.company.hotels.business.repository.BookingRepository;
import ua.com.company.hotels.business.repository.GuestRepository;
import ua.com.company.hotels.business.repository.RoomRepository;
import ua.com.company.hotels.business.repository.StatusRepository;
import ua.com.company.hotels.business.service.BookingService;
import ua.com.company.hotels.business.util.Constants;
import ua.com.company.hotels.business.util.DateParser;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public BookingDTO save(SaveBookingDTO saveBookingDTO) {
        Assert.notNull(saveBookingDTO, "Booking is null");

        // створ formatter, говоримо в якому форматі нам буде приходити дата
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        // переводимо String в LocalDateTime методом parse
        // final String date = saveBookingDTO.getCheckInDate();
       // final LocalDateTime checkInDate = LocalDate.parse(date, formatter).atStartOfDay();
       // final LocalDateTime checkOutDate = LocalDate.parse(saveBookingDTO.getCheckOutDate(), formatter).atStartOfDay();

        final LocalDateTime checkInDate = DateParser.parseToDate(saveBookingDTO.getCheckInDate());
        final LocalDateTime checkOutDate = DateParser.parseToDate(saveBookingDTO.getCheckOutDate());

        final Booking booking = new Booking();
        booking.setGuestId(saveBookingDTO.getGuestId());
        booking.setRoomId(saveBookingDTO.getRoomId());
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setComment(saveBookingDTO.getComment());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setStatusId(Constants.ACTIVE);

        final Booking savedBooking = bookingRepository.save(booking);
        return makeBookingDTO(savedBooking);
    }

    @Override
    public UpdateReturnBookingDTO update(UpdateBookingDTO updateBookingDTO, Long id) {
        Assert.notNull(updateBookingDTO, "Booking is null");
        Assert.notNull(id, "Id is null");

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
//        final String date = updateBookingDTO.getCheckInDate();
//        final LocalDateTime checkInDate = LocalDate.parse(date, formatter).atStartOfDay();
//        final LocalDateTime checkOutDate = LocalDate.parse(updateBookingDTO.getCheckOutDate(), formatter).atStartOfDay();

        final LocalDateTime checkInDate = DateParser.parseToDate(updateBookingDTO.getCheckInDate());
        final LocalDateTime checkOutDate = DateParser.parseToDate(updateBookingDTO.getCheckOutDate());

        final Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Booking not found"));
        booking.setComment(updateBookingDTO.getComment());
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        final Booking updatedBooking = bookingRepository.saveAndFlush(booking);
        return new UpdateReturnBookingDTO(updatedBooking.getId(),
                updatedBooking.getCheckInDate(),
                updatedBooking.getCheckOutDate(),
                updatedBooking.getComment());
    }

    @Override
    public boolean updateBookingRoom(long roomId, long id) {
        final Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Booking not found"));
        final Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new NullPointerException("Room not found"));
        booking.setRoomId(room.getId());
        // для того, щоб відбулася зміна кімнати ми викор метод saveAndFlush
        bookingRepository.saveAndFlush(booking);
        return true;
    }

    @Override
    public boolean updateStatus(long statusId, long id) {
        final Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Booking not found"));
        final Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new NullPointerException("Status not found"));
        booking.setStatusId(status.getId());
        bookingRepository.saveAndFlush(booking);
        return true;
    }

    @Override
    public ResponseBookingDTO findAll(int page) {
        final List<BookingDTO> bookings = bookingRepository.findBookings(PageRequest.of(page, Constants.ENTITY_LIMIT));
        final long count = bookingRepository.count();
        return new ResponseBookingDTO(bookings, count);
    }

    @Override
    public BookingDetailDTO findById(long id) {
        final Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Booking not found"));

//        booking.getGuestId()

        final GuestDTO guest = guestRepository.findGuest(booking.getGuestId())
                .orElseThrow(() -> new NullPointerException("Guest not found"));

//        booking.getRoomId()

        final Room room = roomRepository.findById(booking.getRoomId())
                .orElseThrow(() -> new NullPointerException("Room not found"));

        final BookingDetailDTO bookingDetailDTO = new BookingDetailDTO(booking.getCheckInDate(), booking.getCheckOutDate(), booking.getCreatedAt());
        bookingDetailDTO.setId(booking.getId());
        bookingDetailDTO.setStatusId(booking.getStatusId());
        bookingDetailDTO.setComment(booking.getComment());

        bookingDetailDTO.setGuest(guest);

        bookingDetailDTO.setRoom(room);

        return bookingDetailDTO;
    }

    private BookingDTO makeBookingDTO(Booking booking) {
        return new BookingDTO(booking.getId(),
                booking.getStatusId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getCreatedAt());
    }
}
