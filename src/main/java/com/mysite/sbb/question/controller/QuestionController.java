package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questions = questionService.getList();

        model.addAttribute("questions", questions);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        Question question = questionService.findById(id);

        model.addAttribute("question", question);
        return "question_detail";
    }
}
