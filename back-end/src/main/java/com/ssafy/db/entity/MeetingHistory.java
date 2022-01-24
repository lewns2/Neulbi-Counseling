package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class MeetingHistory extends BaseEntity {

    String action; // CREATE, JOIN, EXIT
    Date insertedTime; // 접속 시간
    
    // 외래키 관계 설정 아직 안함
}
