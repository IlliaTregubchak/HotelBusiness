package ua.com.company.hotels.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.company.hotels.business.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
