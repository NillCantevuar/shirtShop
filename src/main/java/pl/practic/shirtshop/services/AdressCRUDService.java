package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.AdressRepository;

@Service
public class AdressCRUDService implements CRUDService<Adress> {

    @Autowired
    AdressRepository adressRepository;

    @Override
    public Adress find(int id) {
        return adressRepository.getOne(id);
    }

    //Dostaje DTO zapisuje Encje
    @Override
    public int save(Adress adress) {
        adressRepository.save(adress);
        return adress.getId();
    }

    @Override
    public int update(Adress adress, int id) {
        Adress pulledAdress = adressRepository.getOne(id);

        pulledAdress.setState(adress.getState());
        pulledAdress.setPostalCode(adress.getPostalCode());
        pulledAdress.setFlatNumber(adress.getFlatNumber());
        pulledAdress.setCity(adress.getCity());
        pulledAdress.setHouseNumber(adress.getHouseNumber());
        pulledAdress.setStreet(adress.getStreet());
        pulledAdress.setCustomer(adress.getCustomer());
        adressRepository.save(pulledAdress);

        return pulledAdress.getId();
    }

    @Override
    public void delete(int id) {
        adressRepository.deleteById(id);
    }
}
