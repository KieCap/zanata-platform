/*
 * Copyright 2016, Red Hat, Inc. and individual contributors as indicated by the
 * @author tags. See the copyright.txt file in the distribution for a full
 * listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */

package org.zanata.rest.oauth;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Strings;

/**
 * @author Patrick Huang <a href="mailto:pahuang@redhat.com">pahuang@redhat.com</a>
 */
public class OAuthUtil {
    private static final Logger log = LoggerFactory.getLogger(OAuthUtil.class);

    public static Optional<String> getAccessToken(HttpServletRequest request) {
        OAuthAccessResourceRequest oauthRequest = null;
        if (!Strings.isNullOrEmpty(request.getHeader(OAuth.HeaderType.AUTHORIZATION))) {

            try {
                // Make the OAuth Request out of this request and validate it
                // Specify where you expect OAuth access token (request header, body or query string)
                oauthRequest = new
                        OAuthAccessResourceRequest(request, ParameterStyle.HEADER);
                return Optional.of(oauthRequest.getAccessToken());
            } catch (OAuthSystemException e) {
                log.warn("OAuth exception", e);
                return Optional.empty();
            } catch (OAuthProblemException e) {
                log.debug("OAuth problem", e);
                return Optional.empty();
            }
        }
        log.debug("no Authorization header");
        return Optional.empty();

    }
}
