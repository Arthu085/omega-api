package com.omega.api.furnace;


import com.omega.api.furnace.dtos.CreateFurnaceDTO;
import com.omega.api.models.Forno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Forno> getAllFurnaces(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "10") int take,
            @RequestParam(required = false, defaultValue = "0") int skip
    ) {
        var retorno = furnaceService.getAllFurnace(search, take, skip);

        System.out.println(retorno);
        return  retorno;
    }
}
