package ir.adventure.observer.client.service;


import ir.adventure.observer.client.entity.ChatImpl;
import ir.adventure.observer.client.entity.DifferencesData;
import ir.adventure.observer.client.util.CoreRepositories;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by jalil on 10/22/2017.
 */

public class DifferencesService {

    CoreRepositories coreRepositories;
    Long observerId;

    public DifferencesService(CoreRepositories coreRepositories, Long observerId) {

        this.coreRepositories = coreRepositories;
        this.observerId = observerId;
    }


    public ChatImpl getChatById(int chatId) {
        return coreRepositories.getChatRepository().findByIdAndObserverId(Long.valueOf(chatId), observerId);
    }

    public @NotNull HashMap<Integer, int[]> getDifferencesData() {
        HashMap<Integer, int[]> integerHashMap = new HashMap<>();
        Iterable<DifferencesData> differencesDatas = coreRepositories.getDifferencesDataRepository().findByObserverId(observerId);
        for (DifferencesData differencesData : differencesDatas) {
            final int[] data = new int[3];
            data[0] = differencesData.getPts();
            data[1] = differencesData.getDate();
            data[2] = differencesData.getSeq();
            integerHashMap.put(differencesData.getBotId(), data);
        }
        return integerHashMap;
    }


    public DifferencesData getDifferencesData(int botId) {
        return coreRepositories.getDifferencesDataRepository().findByBotIdAndObserverId(botId, observerId);
    }

    public boolean updateDifferencesData(int botId, int pts, int date, int seq) {
        DifferencesData data = coreRepositories.getDifferencesDataRepository().findByBotIdAndObserverId(botId, observerId);
        if (data == null) {
            data = new DifferencesData();
            data.setBotId(botId);
        }
        data.setPts(pts);
        data.setDate(date);
        data.setSeq(seq);
        data.setObserverId(observerId);
        coreRepositories.getDifferencesDataRepository().save(data);
        return true;
    }
}
