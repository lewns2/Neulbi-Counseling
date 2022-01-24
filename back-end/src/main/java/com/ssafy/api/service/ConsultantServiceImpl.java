package com.ssafy.api.service;

import com.ssafy.api.request.ConsultantRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.ConsultantListRes;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.ConsultantRepository;
import com.ssafy.db.repository.ConsultantRepositorySupport;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 상담가 프로필 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("consultantService")
public class ConsultantServiceImpl implements ConsultantService {
    @Autowired
    ConsultantRepository consultantRepository;

    @Autowired
    ConsultantRepositorySupport consultantRepositorySupport;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;

    @Autowired
    UserService userService;

    @Override
    public ConsultantProfile createConsultantProfile(ConsultantRegisterPostReq consultantInfo) {
        ConsultantProfile consultantProfile = new ConsultantProfile();


        consultantProfile.setConsultingCnt(0);
        consultantProfile.setDescription(consultantInfo.getDescription());

        User user = new User();
        TopicCategory topicCategory = new TopicCategory();

        user.setId(consultantInfo.getUserId());
        topicCategory.setId(consultantInfo.getTopicCategoryId());

        consultantProfile.setUser(user);
        consultantProfile.setTopicCategory(topicCategory);


        User consultant = userService.consultantRegister(consultantInfo.getUserId());
        consultant.setConsultant(true);
        userRepository.save(consultant);

        return consultantRepository.save(consultantProfile);
    }


    @Override
    public List<ConsultantListRes> getUsersByConsultant() {

        List<ConsultantListRes> cons = consultantRepositorySupport.findByConsultant(true);
        return cons;
    }

    @Override
    public List<ConsultantListRes> getConsultantByValue(String key, String value) {
        List<ConsultantListRes> cons = null;
        if(key.equals("nickname")){
             cons = userRepositorySupport.findAllByNicknameContains(value);
        }


        return cons;
    }

    @Override
    public List<ConsultantListRes> getUserByTopicCategory(Long topicCategoryId) {
        List<ConsultantListRes> cons = consultantRepositorySupport.findByTopicCategory(topicCategoryId);
        return cons;
    }

    @Override
    public List<User> getUserByRank() {
        List<User> users = userRepositorySupport.findFirst10ByOrderByPointTotDesc();
        return users;
    }


}
