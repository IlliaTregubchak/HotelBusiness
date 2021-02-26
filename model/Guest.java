package ua.com.company.hotels.business.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "guests")
@Entity
public class Guest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    private LocalDateTime birthDate;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "phone_number")
    private int phoneNumber;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
