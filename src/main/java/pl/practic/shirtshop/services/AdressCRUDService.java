package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.AdressDTO;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.repositories.AdressRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;

@Service
public class AdressCRUDService implements CRUDService<AdressDTO> {

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DTOMapper dtoMapper;

    @Override
    public AdressDTO find(Integer id) {
        Adress adress = adressRepository.getOne(id);
        return adress.toDTO();
    }

    @Override
    public Integer save(AdressDTO adress) {
        Adress saved = adressRepository.save(dtoMapper.fromAdressDTO(adress));
        return saved.getId();
    }

    @Override
    public AdressDTO update(AdressDTO adress, Integer id) {
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
    public void delete(Integer id) {
        adressRepository.deleteById(id);
    }
}
