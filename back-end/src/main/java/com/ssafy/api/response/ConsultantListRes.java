package com.ssafy.api.response;

import com.querydsl.core.Tuple;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.Mask;
import com.ssafy.db.entity.TopicCategory;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
public class ConsultantListRes {


	@ApiModelProperty(name="User id")
	Long id;
	@ApiModelProperty(name="User nickname")
	String nickname;
	@ApiModelProperty(name="User ProfileImg")
	String profileImg;
	@ApiModelProperty(name="User pointTot")
	double pointTot;
	@ApiModelProperty(name="ConsultantProfile topicCategoryName")
	String topicCategoryName;
	@ApiModelProperty(name="ConsultantProfile description")
	String description;
	@ApiModelProperty(name="ConsultantProfile consultingCnt")
	int consultingCnt;





	public static Page<ConsultantListRes> of(Page<ConsultantProfile> cons) {
		List<ConsultantListRes> temp = new ArrayList<>();

		Pageable pageable = cons.getPageable();
		long total = cons.getTotalElements();
		int totalPage= cons.getTotalPages();

		for (ConsultantProfile c: cons.getContent()) {
			ConsultantListRes r = new ConsultantListRes();
			User user = c.getUser();
			TopicCategory topicCategory = c.getTopicCategory();

			r.setId(user.getId());
			r.setProfileImg(user.getProfileImg());
			r.setPointTot(user.getPointTot());
			r.setNickname(user.getNickname());
			r.setTopicCategoryName(topicCategory.getName());
			r.setDescription(c.getDescription());
			r.setConsultingCnt(c.getConsultingCnt());

			temp.add(r);


		}

		Page<ConsultantListRes> res = new PageImpl<ConsultantListRes>(temp,pageable,total);

		return res;
	}


}