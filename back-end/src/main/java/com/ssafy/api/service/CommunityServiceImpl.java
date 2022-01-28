package com.ssafy.api.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.db.entity.Community;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.Comment;
import com.ssafy.db.repository.CommunityRepository;
import com.ssafy.db.repository.CommunityRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    CommunityRepositorySupport communityRepositorySupport;

    @Autowired
    UserService userService;

    @Override
    public Page<Community> getAllCommunity(Pageable pageable) {

        return  communityRepositorySupport.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Community createCommunity(CommunityRegisterPostReq registerInfo) {


        User user = userService.getUserById(registerInfo.getUserId());

        Community community = Community.builder()
                .user(user)
                .title(registerInfo.getTitle())
                .description(registerInfo.getDescription())
                .likeCnt(0)
                .viewCnt(0)
                .isDeleted(false)
                .build();

        communityRepository.save(community);
        return null;
    }

    @Override
    public Community getCommunity(Long communityId) {

        Community community = communityRepositorySupport.findById(communityId).orElse(null);

        return community;
    }

    @Override
    public Community deleteCommunity(Long communityId) {

        Community community = communityRepositorySupport.findById(communityId).get();

        community.setDeleted(true);

        return communityRepository.save(community);
    }
}
