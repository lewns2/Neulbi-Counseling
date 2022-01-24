package com.ssafy.api.response;

import com.querydsl.core.Tuple;
import com.ssafy.db.entity.Mask;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
	@ApiModelProperty(name="ConsultantProfile topicCategoryId")
	Long topicCategoryId;
	@ApiModelProperty(name="ConsultantProfile description")
	String description;
	@ApiModelProperty(name="ConsultantProfile consultingCnt")
	int consultingCnt;





	public static List<ConsultantListRes> of(List<User> users) {
		List<ConsultantListRes> res = new ArrayList<ConsultantListRes>();


		for (User u: users) {
			ConsultantListRes r = new ConsultantListRes();
			r.setId(u.getId());
			r.setNickname(u.getNickname());
			r.setPointTot(u.getPointTot());
			r.setProfileImg(u.getProfileImg());

			res.add(r);
		}

		return res;
	}


}