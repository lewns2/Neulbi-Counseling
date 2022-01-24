package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * 주제 범주 모델 정의.
 */
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TopicCategory extends BaseEntity {
    String name; // 카테고리 이름
}
