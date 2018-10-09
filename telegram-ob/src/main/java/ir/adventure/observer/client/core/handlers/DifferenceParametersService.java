/**
 * This file is part of Support Bot.
 *
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package ir.adventure.observer.client.core.handlers;

import ir.adventure.observer.client.entity.DifferencesData;
import ir.adventure.observer.client.service.DifferencesService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Handler for difference information
 * @date 14 of February of 2016
 */
public class DifferenceParametersService  {
    private final ConcurrentHashMap<Integer, DifferenceData> differenceDatas = new ConcurrentHashMap<>();
    private final AtomicBoolean loaded = new AtomicBoolean(false);
    private final Object lock = new Object();
    private final DifferencesService differencesService;

    public DifferencesData getDifferencesData(int chatId){
        return differencesService.getDifferencesData(chatId);
    }

    public DifferenceParametersService(DifferencesService differencesService) {
        this.differencesService = differencesService;
        if (!loaded.get()){
            loadParamsFromDatabase();
        }
    }



    public void setNewUpdateParams(int chatId, @Nullable Integer newPts, @Nullable Integer newSeq, @NotNull Integer newDate) {
        if (!differenceDatas.containsKey(chatId)) {
            create(chatId);
        }
        synchronized (lock) {
            differenceDatas.get(chatId).pts = ((newPts == null) || (newPts == 0)) ? differenceDatas.get(chatId).pts : newPts;
            differenceDatas.get(chatId).seq = ((newSeq == null) || (newSeq == 0)) ? differenceDatas.get(chatId).seq : newSeq;
            differenceDatas.get(chatId).date = (newDate < differenceDatas.get(chatId).date) ? differenceDatas.get(chatId).date : newDate;
            differencesService.updateDifferencesData(chatId, differenceDatas.get(chatId).pts,
                    differenceDatas.get(chatId).date, differenceDatas.get(chatId).seq);
        }
    }

    public int getDate(int chatId) {
        if (!differenceDatas.containsKey(chatId)) {
            create(chatId);
        }
        return differenceDatas.get(chatId).date;
    }


    public int getPts(int chatId) {
        if (!differenceDatas.containsKey(chatId)) {
            create(chatId);
        }
        return differenceDatas.get(chatId).pts;
    }

    public int getSeq(int chatId) {
        if (!differenceDatas.containsKey(chatId)) {
            create(chatId);
        }
        return differenceDatas.get(chatId).seq;
    }


    public boolean mustGetDifferences(int chatId, int pts, @Nullable Integer ptsCount, int seq, @Nullable Integer seqStart) {
        synchronized (lock) {
            boolean mustGetDifferences = false;
            if (pts > 0) {
                final int newPts = getPts(chatId) + ((ptsCount == null) ? 0 : ptsCount);
                if (newPts != pts) {
                    mustGetDifferences = true;
                }
            } else if (seq > 0) {
                final int newSeqStart = (seqStart == null) ? seq : seqStart;
                if ((newSeqStart != (getSeq(chatId) + 1)) && (newSeqStart > getSeq(chatId))) {
                    mustGetDifferences = true;
                }
            }

            return mustGetDifferences;
        }
    }

    private void loadParamsFromDatabase() {
        synchronized (lock) {
            final Map<Integer, int[]> differencesDatas = differencesService.getDifferencesData();

            for (Map.Entry<Integer, int[]> entry : differencesDatas.entrySet()) {
                final DifferenceParametersService.DifferenceData data = new DifferenceParametersService.DifferenceData();
                data.pts = entry.getValue()[0];
                data.date = entry.getValue()[1];
                data.seq = entry.getValue()[2];
                differenceDatas.put(entry.getKey(), data);
            }
            loaded.set(true);
        }
    }

    private void create(int chatId) {
        synchronized (lock) {
            if (!differenceDatas.containsKey(chatId)) {
                differenceDatas.put(chatId, new DifferenceParametersService.DifferenceData());
                differencesService.updateDifferencesData(chatId, 0, 0, 0);
            }
        }
    }

    private class DifferenceData {
        int pts;
        int date;
        int seq;

        DifferenceData() {
            pts = 0;
            date = 0;
            seq = 0;
        }
    }
}
