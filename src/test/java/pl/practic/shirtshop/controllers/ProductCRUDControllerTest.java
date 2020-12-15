package pl.practic.shirtshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.services.ProductCRUDService;
import pl.practic.shirtshop.support.ProductAbility;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}