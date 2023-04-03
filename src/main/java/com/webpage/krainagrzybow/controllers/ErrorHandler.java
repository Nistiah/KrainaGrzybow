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
        //do something like logging
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
//    @ResponseStatus(code = HttpStatus.NOT_FOUND)
//    public String handle404Error(HttpServletRequest request, Exception ex, Model model) {
//        model.addAttribute("errorMessage", "Page not found");
//        log.info("ErrorHandler.handle404Error() errorMessage: " + "Page not found" + "\n\n");
//        return "error";
//    }
//
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
//    public String handle400Error(HttpServletRequest request, Exception ex, Model model) {
//        model.addAttribute("errorMessage", "O co ty sie pytasz?");
//        log.info("ErrorHandler.handle400Error() errorMessage: " + "O co ty sie pytasz?" + "\n\n");
//        return "error";
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleValidationErrors(MethodArgumentNotValidException ex, Model model) {
//        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
//                .map(FieldError::getDefaultMessage)
//                .collect(Collectors.joining(", "));
//        model.addAttribute("errorMessage", errorMessage);
//        log.info("ErrorHandler.handleValidationErrors() errorMessage: " + errorMessage + "\n\n");
//        return "error";
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public String handleAccessDeniedError(HttpServletRequest request, Exception ex, Model model) {
//        model.addAttribute("errorMessage", "Access denied");
//        log.info("ErrorHandler.handleAccessDeniedError() errorMessage: " + "Access denied" + "\n\n");
//        return "error";
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleOtherErrors(HttpServletRequest request, Exception ex, Model model) {
//        model.addAttribute("errorMessage", "Something went wrong");
//        log.info("ErrorHandler.handleOtherErrors() errorMessage: " + "Something went wrong" + "\n\n");
//        return "error";
//    }
}
