package com.tpns.article;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by iainporter on 07/08/2014.
 */
public class BaseCORSFilter {

    private static final Set<String> EMPTY = new HashSet<String>();

    private Set<String> parseAllowedOrigins(String allowedOriginsString) {
        if(!StringUtils.isEmpty(allowedOriginsString)) {
            return new HashSet<String>(Arrays.asList(allowedOriginsString.split(",")));
        } else {
            return EMPTY;
        }

    }

    public Set<String> getAllowedOrigins(String allowedOriginsString) {
        return parseAllowedOrigins(allowedOriginsString);
    }
}
