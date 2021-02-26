package ua.com.company.hotels.business.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.company.hotels.business.dto.bookings.BookingDTO;
import ua.com.company.hotels.business.model.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select new ua.com.company.hotels.business.dto.bookings.BookingDTO(b.id, b.statusId, b.checkInDate, b.checkOutDate, b.createdAt) from Booking b")
    List<BookingDTO> findBookings(Pageable pageable);

}
