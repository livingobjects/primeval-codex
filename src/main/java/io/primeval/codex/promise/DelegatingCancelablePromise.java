package io.primeval.codex.promise;

import java.lang.reflect.InvocationTargetException;

import org.osgi.util.function.Function;
import org.osgi.util.function.Predicate;
import org.osgi.util.promise.Failure;
import org.osgi.util.promise.Promise;
import org.osgi.util.promise.Success;

public abstract class DelegatingCancelablePromise<T> implements CancelablePromise<T> {

    protected final Promise<T> promise;

    public DelegatingCancelablePromise(Promise<T> promise) {
        this.promise = promise;
    }

    // DELEGATION methods

    public boolean isDone() {
        return promise.isDone();
    }

    public T getValue() throws InvocationTargetException,
            InterruptedException {
        return promise.getValue();
    }

    public Throwable getFailure() throws InterruptedException {
        return promise.getFailure();
    }

    public Promise<T> onResolve(Runnable callback) {
        return promise.onResolve(callback);
    }

    public <R> Promise<R> then(Success<? super T, ? extends R> success,
            Failure failure) {
        return promise.then(success, failure);
    }

    public <R> Promise<R> then(Success<? super T, ? extends R> success) {
        return promise.then(success);
    }

    public Promise<T> filter(Predicate<? super T> predicate) {
        return promise.filter(predicate);
    }

    public <R> Promise<R> map(Function<? super T, ? extends R> mapper) {
        return promise.map(mapper);
    }

    public <R> Promise<R> flatMap(
            Function<? super T, Promise<? extends R>> mapper) {
        return promise.flatMap(mapper);
    }

    public Promise<T> recover(
            Function<Promise<?>, ? extends T> recovery) {
        return promise.recover(recovery);
    }

    public Promise<T> recoverWith(
            Function<Promise<?>, Promise<? extends T>> recovery) {
        return promise.recoverWith(recovery);
    }

    public Promise<T> fallbackTo(Promise<? extends T> fallback) {
        return promise.fallbackTo(fallback);
    }

}
