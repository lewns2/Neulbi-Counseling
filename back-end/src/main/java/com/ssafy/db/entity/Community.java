package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Community extends BaseEntity{
    String title; //  게시글 제목
    String description; // 게시글 내용
    int likeCnt; // 받은 공감 갯수
    int viewCnt; // 조회수
    boolean isDeleted; // 글 삭제여부

    // 외래키
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "community") //참조를 당하는 쪽에서 읽기만 가능!
    private List<Comment> commentList = new LinkedList<>();

    public void setDeleted(boolean isDeleted){
        this.isDeleted = isDeleted;
    }

}
