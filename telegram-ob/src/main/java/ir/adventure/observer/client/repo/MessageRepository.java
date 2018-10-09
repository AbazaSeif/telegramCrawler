package ir.adventure.observer.client.repo;

import ir.adventure.observer.client.entity.Message;
import ir.adventure.observer.client.entity.MessagePK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jalil on 10/22/2017.
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, MessagePK> {
}
