package ir.adventure.observer.client.repo;

import ir.adventure.observer.client.entity.MessageView;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jalil on 10/22/2017.
 */
public interface MessageViewRepository extends PagingAndSortingRepository<MessageView, Integer> {
}
