package com.mysite.sbb.answer.controller;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.answer.repository.AnswerRepository;
import com.mysite.sbb.answer.service.AnswerService;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(String content, @PathVariable long id) {
        Question question = questionService.findById(id);
        answerService.write(question, content);
        return "redirect:/question/detail/%s".formatted(id);
    }
}
