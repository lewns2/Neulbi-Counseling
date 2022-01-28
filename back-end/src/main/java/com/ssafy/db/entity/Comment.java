package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

/**
 * 댓글 모델 정의.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Comment extends BaseEntity{
    String description; // 댓글 내용
    int groupNum; // 댓글 그룹
    int layer; // 계층
    boolean isDeleted; // 댓글 삭제여부

    // 외래키
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="community_id")
    Community community;

    public void modifyDescription(String description) { this.description = description; }

    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

}
