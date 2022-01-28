package com.ssafy.db.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Community;
import com.ssafy.db.entity.QComment;
import com.ssafy.db.entity.QCommunity;
import com.ssafy.db.entity.QLikeCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class CommunityRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QCommunity qCommunity = QCommunity.community;
    QComment qComment = QComment.comment;
    QLikeCheck qLikeCheck = QLikeCheck.likeCheck;

    // 게시글 전체 목록 불러오기
    // is_delete = false인 community를 가져온다.
    public Page<Community> findAllByDeletedIsFalse(Pageable pageable) {
        QueryResults<Community> community = jpaQueryFactory
                .select(qCommunity)
                .from(qCommunity)
                .where(qCommunity.isDeleted.eq(false))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset()).fetchResults();


        if (community == null) return Page.empty();

        return new PageImpl<Community>(community.getResults(), pageable, community.getTotal());
    }

    public Optional<Community> findById(Long id) {
        Community community = jpaQueryFactory
                .select(qCommunity)
                .from(qCommunity)
                .where(qCommunity.id.eq(id).and(qComment.isDeleted.eq(false))).distinct().fetchOne();

        if (community == null) return Optional.empty();

        return Optional.ofNullable(community);


    }

}
