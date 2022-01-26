package com.ssafy.api.service;

import com.ssafy.api.request.ConsultantRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.ConsultantListRes;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
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
    TopicCategoryRepository topicCategoryRepository;

    @Autowired
    TopicCategoryRepositorySupport topicCategoryRepositorySupport;

    @Autowired
    UserService userService;

    @Override
    public ConsultantProfile createConsultantProfile(ConsultantRegisterPostReq consultantInfo) {
        User user = userService.getUserById(consultantInfo.getUserId());
        user.registerConsultant(true);

        TopicCategory topicCategory = topicCategoryRepositorySupport.findByTopicCategoryId(consultantInfo.getTopicCategoryId()).orElse(null);



        ConsultantProfile consultantProfile = ConsultantProfile.builder()
                .consultingCnt(0)
                .description(consultantInfo.getDescription())
                .user(user)
                .topicCategory(topicCategory)
                .build();



        userRepository.save(user);

        return consultantRepository.save(consultantProfile);
    }


    @Override
    public Page<ConsultantProfile> getAllConsultant(Pageable pageable) {

        Page<ConsultantProfile> cons = consultantRepositorySupport.findAll(pageable);
        return cons;
    }

    @Override
    public Page<ConsultantProfile> getConsultantByValue(String key, String value, Pageable pageable) {
        Page<ConsultantProfile> cons = null;
        if (key.equals("nickname")) {
            cons = consultantRepositorySupport.findConsultantProfileByUserNicknameContains(value, pageable);
        }else if(key.equals("description")){
            cons = consultantRepositorySupport.findConsultantProfileByDescriptionContains(value, pageable);
        }

        return cons;
    }

    @Override
    public Page<ConsultantProfile> getUserByTopicCategory(Long topicCategoryId, Pageable pageable) {
        Page<ConsultantProfile> cons = consultantRepositorySupport.findAllByTopicCategoryId(topicCategoryId, pageable);
        return cons;
    }

    @Override
    public List<User> getUserByRank( ) {
        List<User> users = userRepositorySupport.findFirst15ByOrderByPointTotDesc();
        return users;
    }


}
