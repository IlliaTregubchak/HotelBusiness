package ua.com.company.hotels.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.company.hotels.business.model.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.price >= :priceLess and r.price <= :priceHigh and r.status = :status and r.category = :category and r.number >= :numberMin and r.number <= :numberMax and r.floor = :floor and r.square >= :squareMin and r.square <= :squareMax")
    List<Room> search(@Param("priceLess") double priceLess, @Param("priceHigh") double priceHigh, @Param("status") boolean status, @Param("category") String category, @Param("numberMin") int numberMin, @Param("numberMax") int numberMax, @Param("floor") int floor, @Param("squareMin") int squareMin, @Param("squareMax") int squareMax);
}
