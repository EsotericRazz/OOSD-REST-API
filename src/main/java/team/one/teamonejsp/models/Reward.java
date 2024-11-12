package team.one.teamonejsp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "rewards", schema = "travelexperts")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RewardId", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "RwdName", length = 50)
    private String rwdName;

    @Size(max = 50)
    @Column(name = "RwdDesc", length = 50)
    private String rwdDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRwdName() {
        return rwdName;
    }

    public void setRwdName(String rwdName) {
        this.rwdName = rwdName;
    }

    public String getRwdDesc() {
        return rwdDesc;
    }

    public void setRwdDesc(String rwdDesc) {
        this.rwdDesc = rwdDesc;
    }

}