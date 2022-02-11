package com.ssafy.api.response;

import com.ssafy.db.entity.Qna;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("QnaResponse")
public class QnaRes {
    @ApiModelProperty(name = "Qna id")
    Long qnaId;
    @ApiModelProperty(name = "User nickname")
    String userNickname;
    @ApiModelProperty(name = "Qna title")
    String title;
    @ApiModelProperty(name = "Qna description")
    String description;
    @ApiModelProperty(name = "Qna description")
    boolean isRocked;
    @ApiModelProperty(name = "Qna date")
    LocalDateTime date;

    public static QnaRes ofDetail(Qna qna) {
        QnaRes res = new QnaRes();

        res.setQnaId(qna.getId());
        res.setUserNickname(qna.getUser().getNickname());
        res.setTitle(qna.getTitle());
        res.setDescription(qna.getDescription());
        res.setRocked(qna.isRocked());

        LocalDateTime modifiedDate = qna.getModifiedDate();
        res.setDate( modifiedDate != null ? modifiedDate : qna.getCreatedDate() );

        return res;
    }

    public static QnaRes OfList(Qna qna) {
        QnaRes res = new QnaRes();

        res.setQnaId(qna.getId());
        res.setUserNickname(qna.getUser().getNickname());
        res.setTitle(qna.getTitle());
        res.setRocked(qna.isRocked());

        LocalDateTime modifiedDate = qna.getModifiedDate();
        res.setDate( modifiedDate != null ? modifiedDate : qna.getCreatedDate() );

        return res;
    }
}
