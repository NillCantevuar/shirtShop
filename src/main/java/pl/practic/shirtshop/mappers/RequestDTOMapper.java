package pl.practic.shirtshop.mappers;

import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.requests.ProductAddRequest;

public class RequestDTOMapper {


    public ProductDTO productAddRequestToDTO(ProductAddRequest productAddRequest){

        ProductDTO productDTO = new ProductDTO(
                null,productAddRequest.getType(),productAddRequest.getBrand(),productAddRequest.getPrice(),productAddRequest.getName(),
                productAddRequest.getStock(),null);
        )


    }



}
