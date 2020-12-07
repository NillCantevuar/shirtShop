package pl.practic.shirtshop.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.practic.shirtshop.dto.AdressDTO;
import pl.practic.shirtshop.repositories.AdressRepository;
import pl.practic.shirtshop.support.AdressAbility;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AdressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AdressAbility adressAbility;

    @Autowired
    private AdressRepository adressRepository;

    @Test
    public void shouldAddAddress() throws Exception {

        //given
        AdressDTO adressDTO = adressAbility.generateOneAdressDTO();
        adressDTO.setCustomerId(1);
        String jason = objectMapper.writeValueAsString(adressDTO);
        //when
         mockMvc.perform(post("/adress/add").contentType("application/json").content(jason)).andExpect(status().isOk());
        //then
        Assert.assertEquals(jason,objectMapper.writeValueAsString(adressRepository.findAll().get(0)));

        //czy dochodzi do service

    }


    // 2. powinien sie wyjebac jak dodam nulla
}
