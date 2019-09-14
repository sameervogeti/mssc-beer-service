package guru.springframework.msscbeerservice.mappers;


import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDTO beerToBeerdto(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);
}
