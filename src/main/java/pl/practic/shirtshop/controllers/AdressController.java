package pl.practic.shirtshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.practic.shirtshop.dto.AdressDTO;
import pl.practic.shirtshop.services.AdressCRUDService;

@Controller("/adress")
public class AdressController {

    @Autowired
    AdressCRUDService adressCRUDService;


    @PostMapping("/add")
    public Integer addAddress(@RequestBody AdressDTO dto){
        return adressCRUDService.save(dto);

    }


}
