package com.ssafy.db.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Comment;
import com.ssafy.db.entity.QComment;
import com.ssafy.db.entity.QCommunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QComment qComment = QComment.comment;
    QCommunity qCommunity = QCommunity.community;

    // 게시글에 해당하는 댓글 목록 불러오기
    // is_delete = false인 comment를 가져온다.
    public Page<Comment> findByCommunityIdAndDeletedIsFalse(Pageable pageable, Long communityId) {
        QueryResults<Comment> cons = jpaQueryFactory
                .select(qComment)
                .from(qComment)
                .where(qComment.community.id.eq(communityId).and(qComment.isDeleted.eq(false)))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset()).fetchResults();


        if (cons == null) return Page.empty();

        return new PageImpl<Comment>(cons.getResults(), pageable, cons.getTotal());
    }

}
