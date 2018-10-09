package ir.adventure.observer.client.repo;

import ir.adventure.observer.client.entity.AccessToken;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jalil on 11/8/2017.
 */
public interface AccessTokenRepository extends PagingAndSortingRepository<AccessToken, Long> {
    List<AccessToken> findByAccessHash(String accessHash);
}
