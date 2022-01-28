package com.ssafy.api.service;

import com.ssafy.api.request.CommentPostReq;
import com.ssafy.api.request.CommentPutReq;
import com.ssafy.db.entity.Comment;
import com.ssafy.db.entity.Community;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.CommentRepository;
import com.ssafy.db.repository.CommentRepositorySupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Autowired
    UserService userService;
    @Autowired
    CommunityService communityService;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentRepositorySupport commentRepositorySupport;

    @Override
    public Page<Comment> getCommentById(Pageable pageable, Long communityId) {
        // 게시글 아이디(communityId)로 댓글 목록 조회
        Page<Comment> commnentList = commentRepositorySupport.findByCommunityIdAndDeletedIsFalse(pageable, communityId);

        return commnentList;
    }

    @Override
    public Comment createComment(CommentPostReq commentInfo) {

        User user = userService.getUserById(commentInfo.getUserId());
        Community community = (Community) communityService.getCommunity(commentInfo.getCommunityId());

        Comment comment = Comment.builder()
                .description(commentInfo.getDescription())
                .layer(commentInfo.getLayer())
                .groupNum(commentInfo.getGroupNum())
                .isDeleted(false)
                .user(user)
                .community(community)
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public Comment modifyComment(CommentPutReq commentInfo) {
        User user = userService.getUserById(commentInfo.getUserId());
        Comment comment = commentRepository.findCommentById(commentInfo.getCommentId()).orElse(null);

        // 댓글 쓴 사람이랑 같지 않거나 or 댓글이 없을 경우 or 이미 삭제된 경우
        if(user.getId() != comment.getId() || comment == null || comment.isDeleted()) return null;

        comment.modifyDescription(commentInfo.getDescription());
        return commentRepository.save(comment);
    }

    @Override
    public Comment deleteComment(Long communityId) {
        Comment comment = commentRepository.findCommentById(communityId).orElse(null);

        comment.setDeleted(true);

        return commentRepository.save(comment);
    }
}
