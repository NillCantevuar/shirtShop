package pl.practic.shirtshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.services.ProductCRUDService;
import pl.practic.shirtshop.support.ProductAbility;

import javax.transaction.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ProductCRUDControllerTest {

    @Autowired
    ProductAbility productAbility;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductCRUDService productCRUDService;

    @Test
    @Transactional
    public void shouldAddProductToDB() throws Exception {
        //given
        productAbility.saveOneProductToDB();
        ProductDTO productDTO = productAbility.generateOneProduct2DTO();
        String productJson = objectMapper.writeValueAsString(productDTO);
        //when
        MvcResult result = mockMvc.perform(post("/api/product/")
                .contentType("application/json")
                .content(productJson))
                .andExpect(status().isOk()).andReturn();
        //then
        Assert.assertEquals(productCRUDService.find(Integer.valueOf(result.getResponse().getContentAsString())).getBrand(),
                productDTO.getBrand());


    }
    @Test
    @Transactional
    public void shouldGetProductFromDB() throws Exception {
        //given
        Integer savedId = productAbility.saveOneProductToDB();
        //when
        MvcResult result = mockMvc.perform(get("/api/product/"+savedId))
                .andExpect(status().isOk()).andReturn();
        //then
        String contentJson =result.getResponse().getContentAsString();
        ProductDTO productDTORecived =objectMapper.readValue(
                contentJson, ProductDTO.class);
        Assert.assertEquals(productCRUDService.find(savedId).getName(),productDTORecived.getName());

    }
    @Test
    @Transactional
    public void shouldGetAllProductsFromDB() throws Exception {
        //given
        Integer savedId1 = productAbility.saveOneProductToDB();
        Integer savedId2 = productAbility.saveOneProductToDB();
        Integer savedId3 = productAbility.saveOneProductToDB();
        //when
        MvcResult result = mockMvc.perform(get("/api/product/"))
                .andExpect(status().isOk()).andReturn();
        //then
        String contentJson =result.getResponse().getContentAsString();
        List<ProductDTO> productsDTOsRecived =objectMapper.readValue(
                contentJson, objectMapper.getTypeFactory().constructCollectionType(List.class,ProductDTO.class));


        Assert.assertEquals(productCRUDService.find(savedId1).getName(),productsDTOsRecived.get(0).getName());
        Assert.assertEquals(productCRUDService.find(savedId2).getName(),productsDTOsRecived.get(1).getName());
        Assert.assertEquals(productCRUDService.find(savedId3).getName(),productsDTOsRecived.get(2).getName());

    }

    @Test
    @Transactional
    public  void shouldDeleteProductFormDB()throws Exception{
        //given
        Integer savedId = productAbility.saveOneProductToDB();
        //when
         mockMvc.perform(delete("/api/product/"+savedId))
                .andExpect(status().isOk());
        //then
        Assert.assertThrows(JpaObjectRetrievalFailureException.class,()->productCRUDService.find(savedId));
    }

    @Test
    @Transactional
    public void shouldUpadateProductInDB() throws  Exception{
        //given
        Integer savedId = productAbility.saveOneProductToDB();
        ProductDTO updatingProductDTO = productAbility.generateOneProduct2DTO();
        String productJson = objectMapper.writeValueAsString(updatingProductDTO);
        //when
        MvcResult result = mockMvc.perform(patch("/api/product/"+savedId)
                .contentType("application/json")
                .content(productJson))
                .andExpect(status().isOk()).andReturn();
        //then
        Assert.assertEquals(productCRUDService.find(Integer.valueOf(result.getResponse().getContentAsString())).getBrand(),
                updatingProductDTO.getBrand());
    }



}
