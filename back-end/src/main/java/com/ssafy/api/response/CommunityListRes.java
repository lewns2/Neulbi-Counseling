package com.ssafy.api.response;

import com.ssafy.db.entity.Community;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.TopicCategory;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * 상담가 목록 조회 API ([GET] /advice) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("ConsultantListRes")
public class CommunityListRes {

	@ApiModelProperty(name="Community id")
	Long id;
	@ApiModelProperty(name="User nickname")
	String userNickname;
	@ApiModelProperty(name="Community title")
	String title;
	@ApiModelProperty(name="Community likeCnt")
	int likeCnt;
	@ApiModelProperty(name="Community viewCnt")
	int viewCnt;

	public static Page<CommunityListRes> of(Page<Community> coms) {
		List<CommunityListRes> temp = new ArrayList<>();

		Pageable pageable = coms.getPageable();
		long total = coms.getTotalElements();

		for (Community c: coms.getContent()) {
			CommunityListRes clr = new CommunityListRes();
			User user = c.getUser();

			clr.setUserNickname(user.getNickname());
			clr.setId(c.getId());
			clr.setTitle(c.getTitle());
			clr.setLikeCnt(c.getLikeCnt());
			clr.setViewCnt(c.getViewCnt());

			temp.add(clr);
		}

		Page<CommunityListRes> res = new PageImpl<CommunityListRes>(temp,pageable,total);

		return res;
	}


}