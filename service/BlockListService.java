package ua.com.company.hotels.business.service;

import ua.com.company.hotels.business.dto.blocks.BlockListDTO;
import ua.com.company.hotels.business.dto.blocks.EditBlockDTO;

import java.util.List;

public interface BlockListService {

    BlockListDTO save(EditBlockDTO editBlockDTO);

    BlockListDTO update(EditBlockDTO editBlockDTO, Long id);

    List<BlockListDTO> findAll();

    String delete(Long id);

}
