package com.ssafy.api.service;

import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.db.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityService {

    Page<Community> getAllCommunity(Pageable pageable);
    Community createCommunity(CommunityRegisterPostReq registerInfo);
    Community getCommunity(Long communityId);
    Community deleteCommunity(Long communityId);
}
