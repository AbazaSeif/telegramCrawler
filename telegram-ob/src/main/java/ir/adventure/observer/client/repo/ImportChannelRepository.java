package ir.adventure.observer.client.repo;

import ir.adventure.observer.client.entity.ImportChannel;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jalil on 10/22/2017.
 */
public interface ImportChannelRepository extends PagingAndSortingRepository<ImportChannel, Long> {
    ImportChannel findByChatId(Long channelId);
    List<ImportChannel> findByObserverId(Long observerId);

}

