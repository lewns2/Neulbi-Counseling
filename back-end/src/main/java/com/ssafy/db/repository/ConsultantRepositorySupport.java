package com.ssafy.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.response.ConsultantListRes;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.QConsultantProfile;
import com.ssafy.db.entity.QUser;
import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public List<ConsultantListRes> findByConsultant(boolean flag) {
        List<Tuple> temp = jpaQueryFactory
                .select(qUser.id, qUser.nickname, qUser.pointTot, qConsultantProfile.consultingCnt, qConsultantProfile.topicCategory.id, qConsultantProfile.description)
                .from(qUser)
                .join(qConsultantProfile).on(qUser.id.eq(qConsultantProfile.user.id))
                .fetch();

        return getConsultantListRes(temp);

    }


    private List<ConsultantListRes> getConsultantListRes(List<Tuple> temp) {
        if (temp == null) return Collections.emptyList();

        List<ConsultantListRes> cons = new ArrayList<>();
        for (Tuple t : temp) {
            ConsultantListRes con = new ConsultantListRes();
            con.setId(t.get(qUser.id));
            con.setTopicCategoryId(t.get(qConsultantProfile.topicCategory.id));
            con.setNickname(t.get(qUser.nickname));
            con.setPointTot(t.get(qUser.pointTot));
            con.setProfileImg(t.get(qUser.profileImg));
            con.setConsultingCnt(t.get(qConsultantProfile.consultingCnt));
            con.setDescription(t.get(qConsultantProfile.description));

            cons.add(con);
        }

        return cons;
    }

    // 고민상담 - 상담가 목록 불러오기 끝
    public List<ConsultantListRes> findByTopicCategory(Long topicCategoryId) {
        List<Tuple> temp = jpaQueryFactory
                .select(qUser.id, qUser.nickname, qUser.pointTot, qConsultantProfile.consultingCnt, qConsultantProfile.topicCategory.id, qConsultantProfile.description)
                .from(qUser)
                .join(qConsultantProfile).on(qUser.id.eq(qConsultantProfile.user.id))
                .where(qConsultantProfile.topicCategory.id.eq(topicCategoryId)).fetch();

        return getConsultantListRes(temp);
    }




}
