package com.omega.api.forno;

import com.omega.api.forno.dtos.CreateFornoDto;
import com.omega.api.forno.dtos.UpdateFornoDto;
import com.omega.api.models.Forno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fornos")
public class FornoController {

    @Autowired
    private FornoService fornoService;

    @PostMapping("/create")
    public ResponseEntity<Void> createForno(@RequestBody CreateFornoDto createFornoDto) {
        fornoService.create(createFornoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllFornos(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "10") int take,
            @RequestParam(required = false, defaultValue = "0") int skip) {
        List<Forno> fornos = fornoService.getAllFornos(search, take, skip);
        long total = fornos.size();

        int pages = (int) Math.ceil((double) total / take);

        Map<String, Object> response = new HashMap<>();
        response.put("data", fornos);
        response.put("total", total);
        response.put("pages", pages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateForno(@PathVariable Long id, @RequestBody UpdateFornoDto dto) {
        fornoService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteForno(@PathVariable Long id) {
        fornoService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

}
