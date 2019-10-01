package guru.springframework.msscbeerservice.services;


import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.mappers.BeerMapper;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerDTO;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.enums.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    @Override
    public BeerDTO getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException {
        return beerMapper.beerToBeerdto(beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public BeerDTO saveBeer(BeerDTO beerDTO) {
        return beerMapper.beerToBeerdto(beerRepository.save(beerMapper.beerDtoToBeer(beerDTO)));
    }

    @Override
    public BeerDTO updateBeerByID(UUID beerId, BeerDTO beerDTO) throws ChangeSetPersister.NotFoundException {
        Beer beer = beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        beer.setBeerName(beerDTO.getBeerName());
        beer.setBeerStyle(beerDTO.getBeerStyle().name());
        beer.setPrice(beerDTO.getPrice());
        beer.setUpc(beerDTO.getUpc());
        return beerMapper.beerToBeerdto(beerRepository.save(beer));

    }

    @Override
    public void deleteBeerbyId(UUID beerId) {
        log.info("Delete Invoked");

    }

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle))
        {
            beerPage=beerRepository.findAllByBeerNameAndBeerStyle(beerName,beerStyle,pageRequest);
        }
        else if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle))
        {
            beerPage=beerRepository.findAllByBeerName(beerName,pageRequest);
        }
        else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle))
        {
            beerPage=beerRepository.findAllByBeerStyle(beerStyle,pageRequest);
        }
        else
        {
            beerPage=beerRepository.findAll(pageRequest);
        }
        beerPagedList=new BeerPagedList(beerPage.getContent()
                .stream()
                .map(beerMapper::beerToBeerdto)
                .collect(Collectors.toList()),
                PageRequest.of(
                        beerPage.getPageable().getPageNumber()
                        ,beerPage.getPageable().getPageSize())
                ,beerPage.getTotalElements());
        return beerPagedList;
    }


}
