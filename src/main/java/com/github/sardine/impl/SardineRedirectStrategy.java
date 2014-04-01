/*
 * Copyright 2009-2011 Jon Stevens et al.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.sardine.impl;

import ch.boye.httpclientandroidlib.HttpEntityEnclosingRequest;
import ch.boye.httpclientandroidlib.HttpRequest;
import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.ProtocolException;
import ch.boye.httpclientandroidlib.client.methods.HttpEntityEnclosingRequestBase;
import ch.boye.httpclientandroidlib.client.methods.HttpUriRequest;
import ch.boye.httpclientandroidlib.impl.client.DefaultRedirectStrategy;
import ch.boye.httpclientandroidlib.protocol.HttpContext;

import com.github.sardine.impl.methods.HttpPropFind;

/**
 * @version $Id:$
 */
public class SardineRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    protected boolean isRedirectable(String method) {
        if(super.isRedirectable(method)) {
            return true;
        }
        return method.equalsIgnoreCase(HttpPropFind.METHOD_NAME);
    }

    @Override
    public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context)
            throws ProtocolException {
        String method = request.getRequestLine().getMethod();
        if(method.equalsIgnoreCase(HttpPropFind.METHOD_NAME)) {
            return this.copyEntity(new HttpPropFind(this.getLocationURI(request, response, context)), request);
        }
        return super.getRedirect(request, response, context);
    }

    private HttpUriRequest copyEntity(
            final HttpEntityEnclosingRequestBase redirect, final HttpRequest original) {
        if(original instanceof HttpEntityEnclosingRequest) {
            redirect.setEntity(((HttpEntityEnclosingRequest) original).getEntity());
        }
        return redirect;
    }
}
