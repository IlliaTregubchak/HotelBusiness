package ua.com.company.hotels.business.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "rooms")
@Entity
public class Room {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "number")
    private int number;
    @Column(name = "floor")
    private int floor;
    @Column(name = "square")
    private int square;
    @Column(name = "smoking")
    private boolean smoking;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private double price;
    @Column(name = "status")
    private boolean status;
}
