package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * 주제 범주 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class TopicCategory extends BaseEntity {
    String name; // 카테고리 이름
}
