package com.omega.api.users;

import com.omega.api.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<Map<String,Object>> listarUsuarios(@RequestParam(required = false, defaultValue = "") String search,
                                                            @RequestParam(required = false, defaultValue = "10") int take,
                                                            @RequestParam(required = false, defaultValue = "0") int skip) {
        List<Usuario> usuarios = usersService.getAllUsers(search, take, skip);

        long total = usuarios.size();

        int pages = (int) Math.ceil((double) total / take);

        Map<String, Object> response = new HashMap<>();
        response.put("data", usuarios);
        response.put("total", total);
        response.put("pages", pages);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
