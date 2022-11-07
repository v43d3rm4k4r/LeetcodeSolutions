package leetcode.solutions;

@FunctionalInterface
public interface SolutionsFactory<T extends LeetcodeSolution> {
    T create();
}