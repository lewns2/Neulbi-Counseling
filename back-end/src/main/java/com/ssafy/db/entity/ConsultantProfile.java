package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 상담가 프로필 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class ConsultantProfile extends BaseEntity{
    String description; // 한줄 소개(50자 제한)
    int consultingCnt; // 상담한 횟수

    // 외래키
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="topic_category_id")
    TopicCategory topicCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    User user;


}
