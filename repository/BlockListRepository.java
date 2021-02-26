package ua.com.company.hotels.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.company.hotels.business.dto.blocks.BlockListDTO;
import ua.com.company.hotels.business.model.BlockList;

import java.util.List;

public interface BlockListRepository extends JpaRepository<BlockList, Long> {

    @Query("select new ua.com.company.hotels.business.dto.blocks.BlockListDTO(bl.id, bl.name, bl.note, bl.createdAt) from BlockList bl")
    List<BlockListDTO> findBlockLists();
}
