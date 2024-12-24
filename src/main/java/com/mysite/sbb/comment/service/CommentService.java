package com.mysite.sbb.comment.service;

import com.mysite.sbb.comment.entity.Comment;
import com.mysite.sbb.comment.repository.CommentRepository;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment write(Question question, SiteUser author, String content) {
        Comment comment = Comment.builder()
                .content(content)
                .question(question)
                .author(author)
                .build();
        commentRepository.save(comment);
        return comment;
    }
}
