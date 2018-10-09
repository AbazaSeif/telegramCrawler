package ir.adventure.observer.service;

import ir.adventure.observer.client.entity.AccessToken;
import ir.adventure.observer.client.repo.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jalil on 11/8/2017.
 */
@Service
public class AccessService {
    @Autowired
    AccessTokenRepository accessTokenRepository;

    public Boolean checkAccessHash(String accessHash){
        List<AccessToken> byAccessHash = accessTokenRepository.findByAccessHash(accessHash);
        if (byAccessHash.isEmpty()){
            return false;
        }
        return true;
    }
}
