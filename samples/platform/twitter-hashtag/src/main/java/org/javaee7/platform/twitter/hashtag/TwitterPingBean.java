/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.javaee7.platform.twitter.hashtag;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 * @author Arun Gupta
 */
@Stateless
public class TwitterPingBean {
    
    WebTarget target;
    
    @PostConstruct
    private void init() {
        Client client = ClientBuilder.newClient();
//        client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));
        target = client
                .target("https://api.twitter.com/1.1/search/tweets.json")
                .queryParam("q", "%23javaee7")
                .queryParam("result_type", "mixed")
                .queryParam("count", "100");
    }

//    @Schedule(hour = "*", minute = "1", second = "0", persistent = false)
    public void pingTwitter() {
        System.out.println("pinging Twitter ...");
        target.queryParam("since_id", "335724844056449027");
        
        String response = target
                .request(MediaType.TEXT_PLAIN)
                .header("Authorization", "OAuth oauth_consumer_key=\"myE71Pd0EDFGnisST8XQ\", oauth_nonce=\"f4f1506b69e50ff53b7416cff1137a32\", oauth_signature=\"6IeMHOlgyBy4W75vFZRZTrzuIs8%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1371254570\", oauth_token=\"12387972-28nXVXAmjdtfb83Y6s3Qf1hPIurv7YrGKhxmjEnrJ\", oauth_version=\"1.0\"")
                .get(String.class);
        System.out.println(response);
    }
    
    public void pingFacebook() {
            Client client = ClientBuilder.newClient();
            WebTarget t = client.target("https://graph.facebook.com/search?q=java&type=post");
            
            JsonObject object = t.request().get(JsonObject.class);
            JsonArray results = object.getJsonArray("data");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                System.out.println("Name ----> " + result.getJsonObject("from").getString("name") + "<br>");
                System.out.println("Message ----> " + result.getString("message", "") + "<br>");
                System.out.println("<p><p>");
            }

    }

}
