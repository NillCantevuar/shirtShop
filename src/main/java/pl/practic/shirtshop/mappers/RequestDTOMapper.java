package pl.practic.shirtshop.mappers;

import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.requests.ProductAddRequest;

public class RequestDTOMapper {


    public static ProductDTO productAddRequestToDTO(ProductAddRequest productAddRequest){

        return new ProductDTO(
                null,productAddRequest.getType(),productAddRequest.getBrand(),productAddRequest.getPrice(),productAddRequest.getName(),
                productAddRequest.getStock(),null);
    }



}
