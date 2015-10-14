package org.zanata.webtrans.server;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

import net.customware.gwt.dispatch.shared.Action;

/**
 * Specifies the Action for the given action handler
 *
 */
@Qualifier
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface ActionHandlerFor {
    /**
     * @return the component name
     */
    @Nonbinding
    Class<? extends Action<?>> value();
}
