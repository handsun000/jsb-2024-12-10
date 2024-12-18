package com.mysite.sbb.answer.service;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.answer.repository.AnswerRepository;
import com.mysite.sbb.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer write(Question question, String content) {
        Answer answer = Answer.builder()
                .content(content)
                .question(question)
                .build();
        return answerRepository.save(answer);
    }
}
