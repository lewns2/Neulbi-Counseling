package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * 가면 모델 정의.
 */
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mask extends BaseEntity {
    String name; // 가면 이름
    String img; // 가면 주소

    @Builder
    public Mask(String name, String img) {
        this.name = name;
        this.img = img;
    }

}
