package pl.practic.shirtshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.mappers.RequestDTOMapper;
import pl.practic.shirtshop.requests.ProductAddRequest;
import pl.practic.shirtshop.services.ProductCRUDService;

import java.util.List;

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

    @GetMapping("/{findId}")
    public @ResponseBody ProductDTO getProduct(@PathVariable Integer findId){
        return productCRUDService.find(findId);
    }

    @GetMapping
    public @ResponseBody List<ProductDTO> getAllProducts(){
        return productCRUDService.findAll();
    }

    @DeleteMapping("/{deleteId}")
    public void deleteProduct(@PathVariable Integer deleteId){
            productCRUDService.delete(deleteId);
    }

    @PatchMapping("/{updateId}")
    public Integer updateProduct(@PathVariable Integer updateId,@RequestBody ProductDTO productDTO){
        return productCRUDService.update(productDTO,updateId);
        //Zmiana RequestBody na updateProductDTO ?
    }
}
