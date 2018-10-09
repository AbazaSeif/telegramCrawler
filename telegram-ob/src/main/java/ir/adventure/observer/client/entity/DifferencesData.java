package ir.adventure.observer.client.entity;

import javax.persistence.*;

/**
 * Created by jalil on 10/23/2017.
 */
@Entity
@Table(name = "obs_DifferencesData")
@Cacheable(false)
public class DifferencesData {
    public DifferencesData(Integer botId, int pts, int date, int seq) {
        this.botId = botId;
        this.pts = pts;
        this.date = date;
        this.seq = seq;
    }

    public DifferencesData() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer botId;

    private Integer pts;
    private Integer date;
    private Integer seq;
    private Long observerId;

    public Long getObserverId() {
        return observerId;
    }

    public void setObserverId(Long observerId) {
        this.observerId = observerId;
    }

    public Integer getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Transient
    public boolean isBlank() {
        return pts == 0 && seq == 0 && date == 0;
    }
}
