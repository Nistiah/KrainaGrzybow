package com.webpage.krainagrzybow.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ErrorHandler  implements ErrorController {

    boolean redirected = false;
    @RequestMapping("/error")
    public String handleError(Model model,HttpServletRequest request) {
        model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        log.info("errorHandler.handleError() errorMessage: " + request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) + "\n\n");
        redirected = !redirected;
        if(!redirected)

            return "redirect:/error";
        else{
            redirected = false;
            return "error";
        }
    }

}
