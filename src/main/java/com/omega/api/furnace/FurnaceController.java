package com.omega.api.furnace;


import com.omega.api.furnace.dtos.CreateFurnaceDTO;
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
public class FurnaceController {

    @Autowired
    FurnaceService furnaceService;


    @PostMapping("/create")
    public ResponseEntity<Void> createFurnace(@RequestBody CreateFurnaceDTO createFurnaceDTO){
        furnaceService.create(createFurnaceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public Map<String, Object> getAllFurnaces(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "10") int take,
            @RequestParam(required = false, defaultValue = "0") int skip
    ) {
        List<Forno> fornos = furnaceService.getAllFurnace(search, take, skip);
        long total = fornos.size();

        int pages = (int) Math.ceil((double) total / take);

        Map<String, Object> response = new HashMap<>();
        response.put("data", fornos);
        response.put("total", total);
        response.put("pages", pages);

        return response;
    }
}
