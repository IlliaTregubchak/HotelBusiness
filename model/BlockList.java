package ua.com.company.hotels.business.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
// показує, відображенням якої саме таблиці є цей клас
@Table(name = "block_lists")
// цей клас є відображенням таблиці БД
@Entity
public class BlockList {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "note")
    private String note;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    // todo add variable LocalDataTime createdAt - make additional column in block_lists Database
}
