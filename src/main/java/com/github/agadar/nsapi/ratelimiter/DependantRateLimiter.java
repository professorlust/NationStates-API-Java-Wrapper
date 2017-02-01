package com.github.agadar.nsapi.ratelimiter;

/**
 * Enforces thread-safe rate limiting for x requests per y milliseconds. This
 * rate limiter is dependant on another rate limiter.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class DependantRateLimiter extends RateLimiter {

    /**
     * The rate limiter this one is dependant on.
     */
    private final RateLimiter dependant;

    /**
     * Constructs a new RateLimiter.
     *
     * @param requests the x in 'x requests per y milliseconds'
     * @param milliseconds the y in 'x requests per y milliseconds'
     * @param dependant the rate limiter this one is dependant on
     */
    public DependantRateLimiter(int requests, int milliseconds, RateLimiter dependant) {
        super(requests, milliseconds);
        assert dependant != null;
        this.dependant = dependant;
    }

    @Override
    public boolean Lock() {
        return super.Lock() ? dependant.Lock() : false;
    }

    @Override
    public void Unlock() {
        dependant.Unlock();
        super.Unlock();
    }
}
