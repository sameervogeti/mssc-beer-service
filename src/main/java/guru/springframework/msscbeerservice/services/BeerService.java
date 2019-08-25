package guru.springframework.msscbeerservice.services;


import guru.springframework.msscbeerservice.web.model.BeerDTO;

import java.util.UUID;

public interface BeerService {
    BeerDTO getBeerById(UUID beerId);

    BeerDTO saveBeer(BeerDTO beerDTO);

    void updateBeerByID(UUID beerId, BeerDTO beerDTO);

    void deleteBeerbyId(UUID beerId);
}
