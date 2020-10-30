package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.AdressDTO;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.AdressRepository;

@Service
public class AdressCRUDService implements CRUDService<AdressDTO> {

    @Autowired
    AdressRepository adressRepository;

    //Zwracam DTO
    @Override
    public AdressDTO find(int id) {
        Adress adress = adressRepository.getOne(id);
        return adress.toDTO();
    }

    //Dostaje DTO zapisuje Encje
    @Override
    public int save(AdressDTO adress) {
        Adress saved = adressRepository.save(Adress.fromDTO(adress));
        return saved.getId();
    }

    //Dostaje DTO zapisuje Encje
    @Override
    public int update(AdressDTO adress, int id) {
        Adress pulledAdress = adressRepository.getOne(id);

        pulledAdress.setState(adress.getState());
        pulledAdress.setPostalCode(adress.getPostalCode());
        pulledAdress.setFlatNumber(adress.getFlatNumber());
        pulledAdress.setCity(adress.getCity());
        pulledAdress.setHouseNumber(adress.getHouseNumber());
        pulledAdress.setStreet(adress.getStreet());
        //pulledAdress.setCustomer(adress.getCustomer());
        //TODO
        //pobieram Custoomera dzieki danemu Id i dołączam go do obiektu
        adressRepository.save(pulledAdress);

        return pulledAdress.getId();
    }

    @Override
    public void delete(int id) {
        adressRepository.deleteById(id);
    }
}
