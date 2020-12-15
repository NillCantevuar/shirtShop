package pl.practic.shirtshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.mappers.RequestDTOMapper;
import pl.practic.shirtshop.requests.ProductAddRequest;
import pl.practic.shirtshop.services.ProductCRUDService;

@RestController
@Transactional
@RequestMapping("/api/product")
public class ProductCRUDController {

    @Autowired
    ProductCRUDService productCRUDService;


    @PostMapping
    public Integer addProduct(@RequestBody ProductAddRequest productAddRequest){
        return productCRUDService.save(RequestDTOMapper.productAddRequestToDTO(productAddRequest));
    }


}
