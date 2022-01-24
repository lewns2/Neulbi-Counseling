package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@ToString
public class Meeting extends BaseEntity{
    Date callStartTime;
    Date callEndTime;
    String title;
    String description;
    boolean isActive;
    int participants;

    // 외래키 관계 설정 아직 안함
}
