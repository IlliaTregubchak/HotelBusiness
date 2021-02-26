package ua.com.company.hotels.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.company.hotels.business.dto.blocks.BlockListDTO;
import ua.com.company.hotels.business.dto.blocks.EditBlockDTO;
import ua.com.company.hotels.business.service.BlockListService;

import java.util.List;

@RestController
@RequestMapping("/api/block-lists")
public class BlockListController {

    @Autowired
    private BlockListService blockListService;

    @PostMapping
    public ResponseEntity<BlockListDTO> save(@RequestBody EditBlockDTO editBlockDTO) {
        final BlockListDTO blockListDTO = blockListService.save(editBlockDTO);
        return new ResponseEntity<>(blockListDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BlockListDTO> update(@RequestBody EditBlockDTO editBlockDTO, @RequestParam long id) {
        final BlockListDTO updateBlockListDTO = blockListService.update(editBlockDTO, id);
        return new ResponseEntity<>(updateBlockListDTO, HttpStatus.OK);
    }

//    cd tomcat
//            cd bin
//            ./startup.sh
//    ./shutdown.sh




    @GetMapping("/findAll")
    public ResponseEntity<List<BlockListDTO>> findAll() {
        final List<BlockListDTO> blockLists = blockListService.findAll();
        return new ResponseEntity<>(blockLists, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        final String deleteBlockList = blockListService.delete(id);
        return new ResponseEntity<>(deleteBlockList, HttpStatus.OK);
    }

}
