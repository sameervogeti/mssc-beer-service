package guru.springframework.msscbeerservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BeerRepository<T, id> extends PagingAndSortingRepository<T, id> {
}
