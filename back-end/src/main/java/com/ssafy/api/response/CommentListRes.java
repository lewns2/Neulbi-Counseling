package com.ssafy.api.response;

import com.ssafy.db.entity.Comment;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@ApiModel("CommentListRes")
public class CommentListRes {

    String description;
    int groupNum;
    int layer;
    String userNickname;
    double userPointTot;

    public static List<CommentListRes> of(List<Comment> commentList) {
        List<CommentListRes> res = new LinkedList<>();


        for (Comment c : commentList) {
            CommentListRes cr = new CommentListRes();
            User user = c.getUser();
            cr.setDescription(c.getDescription());
            cr.setGroupNum(c.getGroupNum());
            cr.setLayer(c.getLayer());
            cr.setUserNickname(user.getNickname());
            cr.setUserPointTot(user.getPointTot());
        }


        return res;
    }


}
