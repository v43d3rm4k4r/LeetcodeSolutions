package leetcode.solutions.annotations;

import leetcode.solutions.complexity.Complexity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProblemSolution {
    Complexity timeComplexity();
    Complexity spaceComplexity();
}