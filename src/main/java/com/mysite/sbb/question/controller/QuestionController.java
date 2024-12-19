package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);

        model.addAttribute("paging", paging);
        return "question/question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        Question question = questionService.findById(id);

        model.addAttribute("question", question);
        return "question/question_detail";
    }

    @GetMapping("/create")
    public String questionCreate() {
        return "question/question_form";
    }

    @PostMapping("/create")
    public String questionCreate(String subject, String content) {
        Question question = questionService.write(subject, content);

        return "redirect:/";
    }
}
