/**
 * Copyright 2020 Pivotal Software, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.instrument.binder.httpcomponents;

import io.micrometer.core.instrument.Tags;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpContext;

class HttpContextUtils {
    static Tags generateTagsForRoute(HttpContext context) {
        String targetScheme = "UNKNOWN";
        String targetHost = "UNKNOWN";
        String targetPort = "UNKNOWN";
        Object routeAttribute = context.getAttribute("http.route");
        if (routeAttribute instanceof HttpRoute) {
            HttpHost host = ((HttpRoute) routeAttribute).getTargetHost();
            targetScheme = host.getSchemeName();
            targetHost = host.getHostName();
            targetPort = String.valueOf(host.getPort());
        }
        return Tags.of(
                "target.scheme", targetScheme,
                "target.host", targetHost,
                "target.port", targetPort
        );
    }
}