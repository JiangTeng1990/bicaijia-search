package com.bicaijia.bcjsearch.ao.index;

import com.bicaijia.bcjsearch.query.IndexQuery;
import com.bicaijia.bcjsearch.result.Result;

public interface ProductIndexAO {
    
    public Result search(IndexQuery indexQuery);
}
