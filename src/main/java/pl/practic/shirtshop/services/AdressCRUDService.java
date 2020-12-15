package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.AdressDTO;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.entities.Product;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.repositories.AdressRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdressCRUDService implements CRUDService<AdressDTO> {

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DTOMapper dtoMapper;

    @Override
    public List<AdressDTO> findAll() {
        return adressRepository.findAll().stream().map(Adress::toDTO).collect(Collectors.toList());
    }

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
    public Integer update(AdressDTO adress, Integer id) {
        Adress toUpadateAdress = adressRepository.getOne(id);

        if ( adress.getCustomerId() != null) {
            toUpadateAdress.setState(adress.getState());
            toUpadateAdress.setPostalCode(adress.getPostalCode());
            toUpadateAdress.setFlatNumber(adress.getFlatNumber());
            toUpadateAdress.setCity(adress.getCity());
            toUpadateAdress.setHouseNumber(adress.getHouseNumber());
            toUpadateAdress.setStreet(adress.getStreet());
            toUpadateAdress.setCustomer(customerRepository.getOne(adress.getId()));
            adressRepository.save(toUpadateAdress);
            return id;

        }

        toUpadateAdress.setState(adress.getState());
        toUpadateAdress.setPostalCode(adress.getPostalCode());
        toUpadateAdress.setFlatNumber(adress.getFlatNumber());
        toUpadateAdress.setCity(adress.getCity());
        toUpadateAdress.setHouseNumber(adress.getHouseNumber());
        toUpadateAdress.setStreet(adress.getStreet());
        toUpadateAdress.setCustomer(null);
        adressRepository.save(toUpadateAdress);
        return id;


    }

    @Override
    public void delete(Integer id) {
        adressRepository.deleteById(id);
    }
}
