package com.bicaijia.bcjsearch.runtime;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Start {
    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();

           WebAppContext webapp = new WebAppContext("src/main/webapp", "/bcjsearch");
           webapp.setDefaultsDescriptor("./src/test/resources/webdefault.xml");
           
           // add mock web.xml here to replace the default web.xml under webapp and enable mockMode
           webapp.setDescriptor("./src/test/resources/web.local.xml");

           Server server = new Server(8080);
           server.setHandler(webapp);
           server.start();
           System.out.println("Jetty Server started, use " + (System.currentTimeMillis() - begin) + " ms");
   }
}
