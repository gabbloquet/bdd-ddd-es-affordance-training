package io.github.gabbloquet.todolist.application.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents a repository of instruments, with a collection-like interface for
 * accessing the domain objects. A repository mediates between the domain and
 * data mapping layers using a collection-like interface for accessing domain
 * objects.
 *
 * @see <a href="http://martinfowler.com/eaaDev/repository.html">Repository</a>
 * @see Aggregate
 */
@Retention(RetentionPolicy.CLASS)
@Inherited
@Documented
public @interface DomainRepository {

    /**
     * The Entity that is the root of the aggregate managed by this repository
     */
    Class<?>[] value() default {};
}
