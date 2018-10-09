package ir.adventure.observer.client.repo;

import ir.adventure.observer.client.entity.ChatImplPK;
import ir.adventure.observer.client.entity.ChatView24;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jalil on 10/22/2017.
 */
public interface ChatView24Repository extends PagingAndSortingRepository<ChatView24, ChatImplPK> {

    List<ChatView24> findById(Integer id);
    List<ChatView24> findByObserverIdAndDeletedIsNullOrderByUpdateView24Asc(Long observerId);
}
