package ir.adventure.observer.client.repo;

import ir.adventure.observer.client.entity.AdMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jalil on 12/4/2017.
 */
public interface AdMessageRepository extends PagingAndSortingRepository<AdMessage, Long> {
    List<AdMessage> findByAdId(Long adId);
}
