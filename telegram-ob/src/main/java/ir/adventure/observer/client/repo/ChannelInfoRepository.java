package ir.adventure.observer.client.repo;

import ir.adventure.observer.client.entity.ChannelInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by jalil on 10/22/2017.
 */
public interface ChannelInfoRepository extends PagingAndSortingRepository<ChannelInfo, Integer> {

    List<ChannelInfo> findTop2ByChannelIdAndView24IsNotNullOrderByTimeDesc(Long channelId);
    List<ChannelInfo> findByChannelIdAndTime(Long channelId, Date date);
    List<ChannelInfo> findByChannelIdAndTimeAndMembersIsNotNull(Long channelId, Date date);
}
