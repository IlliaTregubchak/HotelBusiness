package ua.com.company.hotels.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.com.company.hotels.business.dto.blocks.BlockListDTO;
import ua.com.company.hotels.business.dto.blocks.EditBlockDTO;
import ua.com.company.hotels.business.model.BlockList;
import ua.com.company.hotels.business.repository.BlockListRepository;
import ua.com.company.hotels.business.service.BlockListService;

import java.time.LocalDateTime;
import java.util.List;

//@Transactional - не треба поки що, нема сукупності дій
@Service
public class BlockListServiceImpl implements BlockListService {

    @Autowired
    private BlockListRepository blockListRepository;

    @Override
    public BlockListDTO save(EditBlockDTO editBlockDTO) {
        Assert.notNull(editBlockDTO, "BlockList is null");
        final BlockList blockList = new BlockList();
        blockList.setName(editBlockDTO.getName());
        blockList.setNote(editBlockDTO.getNote());
        blockList.setCreatedAt(LocalDateTime.now());
        final BlockList savedBlockList = blockListRepository.save(blockList);

        return makeBlockListDTO(savedBlockList);
    }

    @Override
    public BlockListDTO update(EditBlockDTO editBlockDTO, Long id) {
        Assert.notNull(editBlockDTO, "BlockList is null");
        Assert.notNull(id, "Id is null");
        final BlockList blockList = blockListRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("BlockList is null"));
        blockList.setName(editBlockDTO.getName());
        blockList.setNote(editBlockDTO.getNote());

        // ЗВЕРТ ДО ОБЄКТА, ЯКИЙ УЖЕ І ТАК Є В БД !!!
        final BlockList updatedBlockList = blockListRepository.saveAndFlush(blockList);

        // заповнюємо конструктор із оновленого обєkта(blockList)

        return makeBlockListDTO(updatedBlockList);
    }

    @Override
    public List<BlockListDTO> findAll() {
        return blockListRepository.findBlockLists();

        // Погана практика: не треба витягувати дані з БД, а потім ще перероблювати
//        final List<BlockListDTO> blockListsDTO = new LinkedList<>();
//        final List<BlockList> blockLists = blockListRepository.findAll();
//
//        blockLists.forEach(s -> {
//            blockListsDTO.add(new BlockListDTO(s.getId(),
//            s.getName(), s.getNote(), s.getCreatedAt()));
//        });
//
//        return blockListsDTO;
    }

    @Override
    public String delete(Long id) {
        Assert.notNull(id, "Id is null");
        // перев чи такий обєкт є в БД
        final BlockList blockList = blockListRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("BlockList is null"));
        blockListRepository.deleteById(blockList.getId());
        return "BlockList was deleted";
    }

    private BlockListDTO makeBlockListDTO(BlockList blockList) {
        return new BlockListDTO(blockList.getId(),
                blockList.getName(),
                blockList.getNote(),
                blockList.getCreatedAt());
    }
}
