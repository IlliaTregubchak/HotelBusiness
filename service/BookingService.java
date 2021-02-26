package ua.com.company.hotels.business.service;

import ua.com.company.hotels.business.dto.bookings.*;

public interface BookingService {

    BookingDTO save(SaveBookingDTO saveBookingDTO);

    // update date (2) comment
    UpdateReturnBookingDTO update(UpdateBookingDTO updateBookingDTO, Long id);

    // мета методу - змінити номер кімнати з VirtualRoom y реальний номер кімнати
    boolean updateBookingRoom(long roomId, long id);

    // мета методу - змінити статус, коли гість вже зачекінився або виселився
    boolean updateStatus(long statusId, long id);

    ResponseBookingDTO findAll(int page);

    BookingDetailDTO findById(long id);
}
