package ir.adventure.observer.client.repo;


import ir.adventure.observer.client.entity.DifferencesData;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jalil on 10/22/2017.
 */
public interface DifferencesDataRepository extends PagingAndSortingRepository<DifferencesData, Integer> {
    DifferencesData findByBotIdAndObserverId(int botId, Long observerId);
    Iterable<DifferencesData> findByObserverId(Long observerId);
}
