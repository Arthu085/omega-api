package com.omega.api.furnace;


import com.omega.api.furnace.dtos.CreateFurnaceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fornos")
public class FurnaceController {

    FurnaceService furnaceService;


    @PostMapping("/create")
    public ResponseEntity<Void> createFurnace(@RequestBody CreateFurnaceDTO createFurnaceDTO){
        furnaceService.create(createFurnaceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
