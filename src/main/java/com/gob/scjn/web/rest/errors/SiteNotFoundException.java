package com.gob.scjn.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class SiteNotFoundException extends AbstractThrowableProblem{
	
    private static final long serialVersionUID = 1L;

    public SiteNotFoundException() {
        super(ErrorConstants.SITE_NOT_FOUND_TYPE, "Site not found", Status.NOT_FOUND);
    }

}
