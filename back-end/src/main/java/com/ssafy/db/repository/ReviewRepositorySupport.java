package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QReview qReview = QReview.review;

}
