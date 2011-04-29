package atomicmap;

/**
 * Like Callable<T>, without the checked exceptions BS.
 */
public interface Lazy<T> {
    T get();
}
