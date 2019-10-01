package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.enums.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
@Slf4j
public class BootStrapLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;

    public BootStrapLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createData();

    }

    private void createData() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder().beerName("Mango Bobs")
                    .beerStyle(BeerStyleEnum.IPA.name())
                    .minOnHand(100).price(new BigDecimal(10)).upc(1000l).build());
            beerRepository.save(Beer.builder().beerName("Santra")
                    .beerStyle(BeerStyleEnum.LAGER.name())
                    .minOnHand(100).price(new BigDecimal(10)).upc(10002l).build());
        }
        log.info("No. of Objects Bootstrapped are : " + beerRepository.count());

    }
}
