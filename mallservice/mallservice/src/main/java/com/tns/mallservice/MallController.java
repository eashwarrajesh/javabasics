package com.tns.mallservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/malls")
public class MallController {

    private static final Logger logger = LoggerFactory.getLogger(MallController.class);

    @Autowired
    private MallService mallService;

    @GetMapping
    public List<Mall> getAllMalls() {
        logger.info("Fetching all malls");
        return mallService.getAllMalls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mall> getMallById(@PathVariable Long id) {
        logger.info("Fetching mall with id: {}", id);
        Optional<Mall> mall = mallService.getMallById(id);
        return mall.map(ResponseEntity::ok)
                   .orElseGet(() -> {
                       logger.warn("Mall not found with id: {}", id);
                       return ResponseEntity.notFound().build();
                   });
    }

    @PostMapping
    public ResponseEntity<Mall> createMall(@Validated @RequestBody Mall mall) {
        logger.info("Creating mall: {}", mall);
        Mall createdMall = mallService.saveMall(mall);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMall);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mall> updateMall(@PathVariable Long id, @Validated @RequestBody Mall mall) {
        logger.info("Updating mall with id: {}", id);
        mall.setId(id);
        Mall updatedMall = mallService.saveMall(mall);
        if (updatedMall == null) {
            logger.warn("Mall not found for update with id: {}", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMall);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMall(@PathVariable Long id) {
        logger.info("Deleting mall with id: {}", id);
        boolean deleted = mallService.deleteMall(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Mall not found for deletion with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
