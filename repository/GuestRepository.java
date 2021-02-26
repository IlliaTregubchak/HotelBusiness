package ua.com.company.hotels.business.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.company.hotels.business.dto.guests.GuestDTO;
import ua.com.company.hotels.business.model.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query("select new ua.com.company.hotels.business.dto.guests.GuestDTO(g.id, g.name, g.birthDate, g.nationality, g.phoneNumber, g.createdAt) from Guest g")
    List<GuestDTO> findGuests(Pageable pageable);

    @Query("select new ua.com.company.hotels.business.dto.guests.GuestDTO(g.id, g.name, g.birthDate, g.nationality, g.phoneNumber, g.createdAt) from Guest g where upper(g.name) like upper(:name)")
    List<GuestDTO> findWithName(@Param("name") String name, Pageable pageable);

    // Якщо не ДТО, то не треба писати select new ua.com.company.hotels.business.dto.guests.GuestDTO !!!
    // where upper(g.name) like upper(:name) --- WHERE має співпадати !!!
    @Query("select count(g.id) from Guest g where upper(g.name) like upper(:name)")
    long countWithName(@Param("name") String name);

    @Query("select new ua.com.company.hotels.business.dto.guests.GuestDTO(g.id, g.name, g.birthDate, g.nationality, g.phoneNumber, g.createdAt) from Guest g where g.id = :id")
    Optional<GuestDTO> findGuest(@Param("id") long id);
}
