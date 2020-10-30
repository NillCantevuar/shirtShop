package pl.practic.shirtshop.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.practic.shirtshop.dto.AdressDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldAddAddress(){

        //given
        AdressDTO adressDTO = new AdressDTO();

        //when

        //then
    }


    // 2. powinien sie wyjebac jak dodam nulla
}
