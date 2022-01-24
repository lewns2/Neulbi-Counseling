package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingCategory extends BaseEntity{
    String name;
}
