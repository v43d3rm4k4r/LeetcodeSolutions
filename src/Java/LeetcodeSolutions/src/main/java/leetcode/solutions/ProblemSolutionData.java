package leetcode.solutions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface ProblemSolutionData {
    int maxArgs()  default 1;
    int minArgs()  default 0;
    int solution() default 1;
}
