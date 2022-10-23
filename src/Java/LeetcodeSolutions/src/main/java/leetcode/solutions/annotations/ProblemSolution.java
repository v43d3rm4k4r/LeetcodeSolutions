package leetcode.solutions.annotations;

import leetcode.solutions.Complexity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface ProblemSolution {
    int maxArgs() default 1;
    int minArgs() default 1;
    Complexity complexity() default Complexity.DEFAULT;
}
