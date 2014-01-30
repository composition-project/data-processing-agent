package eu.ebbits.pwal.api.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used in conjunction with 
 * {@link ReflectiveServicesDelegate}. If a method of a reflective delegate is 
 * annotated with the exposed attribute set to "true", it will be exposed with 
 * the PWAL.
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @see        eu.ebbits.pwal.impl.driver.framework.ReflectiveServicesDelegate
 * @since      PWAL 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PWALServiceAnnotation {

    /** 
     * Checks whether the <code>PWALEvent</code> has been annotated as exposed.
     *
     * @since        PWAL 0.1.0
     */
    boolean exposed();
}
