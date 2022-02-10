package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.MyConsultant;
import com.ssafy.db.entity.QMyConsultant;
import com.ssafy.db.entity.QUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MyConsultantRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QUser qUser = QUser.user;
    QMyConsultant qMyConsultant = QMyConsultant.myConsultant;

    public List<MyConsultant> findMyConsultantListByUserId(Long userId) {

        List<MyConsultant> myConsultantList = jpaQueryFactory.select(qMyConsultant)
                .from(qMyConsultant)
                .where(qMyConsultant.user.id.eq(userId))
                .fetch();

        return myConsultantList;
    }

    public void deleteMyConsultantByUserIdAndConsultantId(Long userId, Long consultantId){
        jpaQueryFactory.delete(qMyConsultant)
                .where(qMyConsultant.user.id.eq(userId)
                .and(qMyConsultant.consultant.id.eq(consultantId))).execute();
    }

    public Optional<MyConsultant> findMyConsultantByUserIdAndConsultantId(Long userId, Long consultantId) {

        MyConsultant myConsultant= jpaQueryFactory.select(qMyConsultant)
                .from(qMyConsultant)
                .where(qMyConsultant.user.id.eq(userId)
                        .and(qMyConsultant.consultant.id.eq(consultantId)))
                .fetchOne();
        // 해당 컨설턴트가 없으면 false
        if (myConsultant ==null) return Optional.empty();
        // 있으면 true
        else return Optional.ofNullable(myConsultant);
    }
}
