package ir.adventure.observer.client.repo;

import ir.adventure.observer.client.entity.ChatImpl;
import ir.adventure.observer.client.entity.ChatImplPK;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jalil on 10/22/2017.
 */
public interface ChatRepository extends PagingAndSortingRepository<ChatImpl, ChatImplPK> {
    ChatImpl findByIdAndObserverId(Long id, Long observerId);
    List<ChatImpl> findById(Long id);

    List<ChatImpl> findByObserverIdAndDeletedIsNullOrderByUpdateView24Asc(Long observerId);
    long countByObserverIdAndDeletedIsNull(Long observerId);
}
