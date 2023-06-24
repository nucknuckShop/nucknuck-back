package com.shop.nucknuckshop.annotation;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PunctuationValidator.class)
public @interface Punctuation {
    int requireCount() default 1;
    String message() default  "개 이상의 특수 문자가 필요합니다";
    Class[] groups() default {};
    Class[] payload() default {};
}
