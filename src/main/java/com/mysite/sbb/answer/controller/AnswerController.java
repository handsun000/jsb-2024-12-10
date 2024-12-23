package com.mysite.sbb.answer.controller;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.answer.entity.form.AnswerForm;
import com.mysite.sbb.answer.repository.AnswerRepository;
import com.mysite.sbb.answer.service.AnswerService;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import com.mysite.sbb.user.entity.SiteUser;
import com.mysite.sbb.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable long id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Question question = questionService.findById(id);
        SiteUser siteUser = userService.findByUsername(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question/question_detail";
        }

        answerService.write(question, answerForm.getContent(), siteUser);
        return "redirect:/question/detail/%s".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modify(AnswerForm answerForm, @PathVariable("id") long id, Principal principal) {
        Answer answer = answerService.findById(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "form/answer_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modify(@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "form/answer_form";
        }
        Answer answer = answerService.findById(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
        }
        answerService.modify(answer, answerForm.getContent());

        return "redirect:/question/detail/%s".formatted(answer.getQuestion().getId());
    }
}
