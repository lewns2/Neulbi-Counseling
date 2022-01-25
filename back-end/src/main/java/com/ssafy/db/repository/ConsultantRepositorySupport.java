package com.ssafy.db.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.QConsultantProfile;
import com.ssafy.db.entity.QUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class ConsultantRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;
    QConsultantProfile qConsultantProfile = QConsultantProfile.consultantProfile;

    // 고민상담 - 상담가 목록 불러오기
    // is_consultant = true인 user를 가져온다.
    public Page<ConsultantProfile> findAll(Pageable pageable) {
        QueryResults<ConsultantProfile> cons = jpaQueryFactory
                .select(qConsultantProfile)
                .from(qConsultantProfile)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset()).fetchResults();


        if (cons == null) return Page.empty();

        return new PageImpl<ConsultantProfile>(cons.getResults(), pageable, cons.getTotal());
    }

    public Page<ConsultantProfile> findConsultantProfileByUserNicknameContains(String nickname, Pageable pageable) {

        QueryResults<ConsultantProfile> cons = jpaQueryFactory
                .select(qConsultantProfile)
                .where(qConsultantProfile.user.nickname.contains(nickname)).fetchResults();

        if (cons == null) return Page.empty();

        return new PageImpl<ConsultantProfile>(cons.getResults(), pageable, cons.getTotal());
    }


    // 고민상담 - 상담가 목록 불러오기 끝
    public Page<ConsultantProfile> findAllByTopicCategoryId(Long topicCategoryId, Pageable pageable) {
        QueryResults<ConsultantProfile> cons = jpaQueryFactory
                .select(qConsultantProfile)
                .from(qConsultantProfile)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .where(qConsultantProfile.topicCategory.id.eq(topicCategoryId)).fetchResults();

        if (cons == null) return Page.empty();

        return new PageImpl<ConsultantProfile>(cons.getResults(), pageable, cons.getTotal());
    }

    public Optional<ConsultantProfile> findByUserId(Long userId) {
        ConsultantProfile con = jpaQueryFactory
                .select(qConsultantProfile)
                .from(qConsultantProfile)
                .where(qConsultantProfile.user.id.eq(userId))
                .fetchOne();

        if(con==null)return Optional.empty();
        return Optional.ofNullable(con);
     }

}
