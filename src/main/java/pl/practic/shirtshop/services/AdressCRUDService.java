package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.AdressDTO;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.AdressRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;

@Service
public class AdressCRUDService implements CRUDService<AdressDTO> {

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public AdressDTO find(int id) {
        Adress adress = adressRepository.getOne(id);
        return adress.toDTO();
    }

    @Override
    public int save(AdressDTO adress) {
        Adress saved = adressRepository.save(Adress.fromDTO(adress));
        return saved.getId();
    }

    @Override
    public AdressDTO update(AdressDTO adress, int id) {
        Adress pulledAdress = adressRepository.getOne(id);

        pulledAdress.setState(adress.getState());
        pulledAdress.setPostalCode(adress.getPostalCode());
        pulledAdress.setFlatNumber(adress.getFlatNumber());
        pulledAdress.setCity(adress.getCity());
        pulledAdress.setHouseNumber(adress.getHouseNumber());
        pulledAdress.setStreet(adress.getStreet());
        pulledAdress.setCustomer(customerRepository.getOne(adress.getId()));
        Adress saved = adressRepository.save(pulledAdress);

        return saved.toDTO();
    }

    @Override
    public void delete(int id) {
        adressRepository.deleteById(id);
    }
}
