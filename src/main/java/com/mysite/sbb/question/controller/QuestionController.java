package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.answer.entity.form.AnswerForm;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.entity.form.QuestionForm;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.question.service.QuestionService;
import com.mysite.sbb.user.entity.SiteUser;
import com.mysite.sbb.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);

        model.addAttribute("paging", paging);
        return "question/question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model, AnswerForm answerForm) {
        Question question = questionService.findById(id);

        model.addAttribute("question", question);
        return "question/question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "form/question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "form/question_form";
        }
        SiteUser siteUser = userService.findByUsername(principal.getName());
        questionService.write(questionForm.getSubject(), questionForm.getContent(), siteUser);

        return "redirect:/";
    }
}
