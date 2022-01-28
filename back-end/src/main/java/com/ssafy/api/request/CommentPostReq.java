package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommentPostRequest")
public class CommentPostReq {
    @ApiModelProperty(name="댓글 작성자")
    Long userId;
    @ApiModelProperty(name="게시글 번호")
    Long communityId;
    @ApiModelProperty(name="댓글 내용")
    String description;
    @ApiModelProperty(name="댓글 그룹")
    int groupNum;
    @ApiModelProperty(name="계층")
    int layer;
}
