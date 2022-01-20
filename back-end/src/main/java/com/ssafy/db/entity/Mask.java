package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * 가면 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class Mask extends BaseEntity {
    String name; // 가면 이름
    String img; // 가면 주소
}
