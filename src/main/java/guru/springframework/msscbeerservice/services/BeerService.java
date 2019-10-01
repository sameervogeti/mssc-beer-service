package guru.springframework.msscbeerservice.services;


import guru.springframework.msscbeerservice.web.model.BeerDTO;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.enums.BeerStyleEnum;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDTO getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException;

    BeerDTO saveBeer(BeerDTO beerDTO);

    BeerDTO updateBeerByID(UUID beerId, BeerDTO beerDTO) throws ChangeSetPersister.NotFoundException;

    void deleteBeerbyId(UUID beerId);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of);
}
