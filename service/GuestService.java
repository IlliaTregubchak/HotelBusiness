package ua.com.company.hotels.business.service;

import ua.com.company.hotels.business.dto.guests.GuestDTO;
import ua.com.company.hotels.business.dto.guests.ResponseGuestDTO;
import ua.com.company.hotels.business.dto.guests.SaveGuestDTO;
import ua.com.company.hotels.business.dto.guests.UpdateGuestDTO;

public interface GuestService {

    GuestDTO save(SaveGuestDTO saveGuestDTO);

    GuestDTO update(UpdateGuestDTO updateGuestDTO, Long id);

    ResponseGuestDTO findAll(int page);

    ResponseGuestDTO findByName(String name, int page);
}
