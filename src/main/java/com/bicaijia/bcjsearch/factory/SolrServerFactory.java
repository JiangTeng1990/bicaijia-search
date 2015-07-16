package com.bicaijia.bcjsearch.factory;

import java.io.IOException;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class SolrServerFactory {
    
    public static SolrServer generateSolrServer(String type) {
        
        if (null == type) {
            return null;
        } else {
            ClassPathResource resource = new ClassPathResource("/config/application.properties");
            HttpSolrServer solrServer = null;
            try {
                Properties property = PropertiesLoaderUtils.loadProperties(resource);
                String solrUrl = (String) property.get("solrUrl");
                if (type.equals("product")) {
                    solrServer = new HttpSolrServer(solrUrl + "/product");
                } else if (type.equals("supplier")) {
                    solrServer = new HttpSolrServer(solrUrl + "/supplier");
                }
               
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            return solrServer;
           
        }
    }
}
