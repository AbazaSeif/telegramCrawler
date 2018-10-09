package ir.adventure.observer.client.repo;


import ir.adventure.observer.client.entity.Observer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jalil on 10/22/2017.
 */
public interface ObserverRepository extends PagingAndSortingRepository<Observer, Long> {
    List<Observer> findByEnabled(Boolean enabled);
    List<Observer> findByReadyToLoad(Boolean ready);
}
