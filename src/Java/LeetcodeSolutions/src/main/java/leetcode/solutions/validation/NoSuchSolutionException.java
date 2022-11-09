package leetcode.solutions.validation;

import org.jetbrains.annotations.NotNull;

/**
 * {@code NoSuchSolutionException} is exception created in order to declare that desired solution not found.
 * You are welcome.
 * @author Daniil Kupriyanov
 */

public final class NoSuchSolutionException extends RuntimeException {

    public NoSuchSolutionException(@NotNull String message) { super(message); }
}