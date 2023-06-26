package com.shop.nucknuckshop.annotation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PunctuationValidator implements ConstraintValidator<Punctuation, String> {

    private static final String REGEX = "[ !@#$%^&*(),.?\":{}|<>]";

    private Integer requiredCount;
    private String message;

    @Override
    public void initialize(Punctuation punctuation) {
        requiredCount = punctuation.requireCount();
        message = punctuation.message();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(requiredCount + message)
               .addConstraintViolation();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(str);

        int count = 0;
        while(matcher.find()){
            count++;
        }

        return count >= requiredCount;
    }
}
