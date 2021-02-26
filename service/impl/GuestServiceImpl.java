package ua.com.company.hotels.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.com.company.hotels.business.dto.guests.GuestDTO;
import ua.com.company.hotels.business.dto.guests.ResponseGuestDTO;
import ua.com.company.hotels.business.dto.guests.SaveGuestDTO;
import ua.com.company.hotels.business.dto.guests.UpdateGuestDTO;
import ua.com.company.hotels.business.model.Guest;
import ua.com.company.hotels.business.repository.GuestRepository;
import ua.com.company.hotels.business.service.GuestService;
import ua.com.company.hotels.business.util.Constants;
import ua.com.company.hotels.business.util.DateParser;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

//
//    public static void main(String[] args) {
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
//        // переводимо String в LocalDateTime методом parse
//        final LocalDateTime birthDate = LocalDate.parse("14.10.2020", formatter).atStartOfDay();
//
//        System.out.println(birthDate);
//        System.out.println(LocalDateTime.now());
//
//    }

    @Override
    public GuestDTO save(SaveGuestDTO saveGuestDTO) {
        Assert.notNull(saveGuestDTO, "Guest is null");

//        final LocalDateTime dateTime = LocalDate.parse("12.05.2018", formatter).atStartOfDay();
        // створ formatter, говоримо в якому форматі нам буде приходити дата
       //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        // переводимо String в LocalDateTime методом parse
        //  final LocalDateTime birthDate = LocalDate.parse(saveGuestDTO.getBirthDate(), formatter).atStartOfDay();
        final LocalDateTime birthDate = DateParser.parseToDate(saveGuestDTO.getBirthDate());

        final Guest guest = new Guest();
        guest.setName(saveGuestDTO.getName());

//        final String textNumber = String.valueOf(312432543);
//        final int i = Integer.parseInt("jhghjbghj");

        guest.setBirthDate(birthDate);
        guest.setNationality(saveGuestDTO.getNationality());
        guest.setPhoneNumber(saveGuestDTO.getPhoneNumber());
        guest.setCreatedAt(LocalDateTime.now());
        // guestRepository.save(guest) - save на збереження
        final Guest savedGuest = guestRepository.save(guest);
        return makeGuestDTO(savedGuest);
    }

    @Override
    public GuestDTO update(UpdateGuestDTO updateGuestDTO, Long id) {
        Assert.notNull(updateGuestDTO, "Guest is null");
        Assert.notNull(id, "Id is null");
        final Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Guest not found"));
        guest.setPhoneNumber(updateGuestDTO.getPhoneNumber());
        // якщо repository повертає обєкт, який не влаштовує, то виносиш результат в окрему
        // змінну і перероблюєш в обєкт, який влаштує
        // saveAndFlush - для update!!!
        final Guest updatedGuest = guestRepository.saveAndFlush(guest);
        return makeGuestDTO(updatedGuest);
    }

    @Override
    public ResponseGuestDTO findAll(int page) {
        final List<GuestDTO> guests = guestRepository.findGuests(PageRequest.of(page, Constants.ENTITY_LIMIT));
        final long count = guestRepository.count();
        return new ResponseGuestDTO(guests, count);
    }

    @Override
    public ResponseGuestDTO findByName(String name, int page) {
        Assert.notNull(name, "Name is null");
        final List<GuestDTO> guests = guestRepository.findWithName("%" + name + "%", PageRequest.of(page, Constants.ENTITY_LIMIT));
        final long count = guestRepository.countWithName(name);
        return new ResponseGuestDTO(guests, count);
    }

    // перетворює модель Guest y GuestDTO
    private GuestDTO makeGuestDTO(Guest guest) {
        return new GuestDTO(guest.getId(),
                guest.getName(),
                guest.getBirthDate(),
                guest.getNationality(),
                guest.getPhoneNumber(),
                guest.getCreatedAt());
    }
}
