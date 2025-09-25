package edu.ccrm.service;
import java.util.List;
import java.util.function.Predicate;

// Minimalistic matcher interface for filtering
@FunctionalInterface
public interface Matcher<X> {
    List<X> filter(Predicate<X> rule);
}
