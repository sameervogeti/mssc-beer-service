package guru.springframework.msscbeerservice.services;


import guru.springframework.msscbeerservice.web.model.BeerDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.UUID;

public interface BeerService {
    BeerDTO getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException;

    BeerDTO saveBeer(BeerDTO beerDTO);

    BeerDTO updateBeerByID(UUID beerId, BeerDTO beerDTO) throws ChangeSetPersister.NotFoundException;

    void deleteBeerbyId(UUID beerId);
}
