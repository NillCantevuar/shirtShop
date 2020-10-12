package pl.practic.shirtshop.support;

import org.springframework.stereotype.Component;
import pl.practic.shirtshop.entities.Adress;

@Component
public class AdressAbility {

    public Adress generateOneAdress(){
        Adress adress = new Adress();
        adress.setStreet("Kolorowa");
        adress.setHouseNumber("12B");
        adress.setCity("Lublin");
        adress.setFlatNumber("51");
        adress.setPostalCode("12-342");
        adress.setState("lubleskie");

        return adress;
    }
}
