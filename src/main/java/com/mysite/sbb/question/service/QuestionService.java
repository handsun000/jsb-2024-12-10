package com.mysite.sbb.question.service;

import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return questionRepository.findAll();
    }

    public Question findById(long id) {
        Optional<Question> op = questionRepository.findById(id);

        if (op.isPresent()) return op.get();
        else throw new RuntimeException("데이터가 없습니다.");
    }


    public Question write(String subject, String content) {
        Question question = Question.builder()
                .subject(subject)
                .content(content)
                .build();
        return questionRepository.save(question);
    }
}
