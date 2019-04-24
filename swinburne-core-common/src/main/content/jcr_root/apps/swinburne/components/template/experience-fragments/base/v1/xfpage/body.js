"use strict";

use([], function () {
    //horrible hack from OOTB Adobe equivalent but can't find a js way to get the child node resource like in java
    var rootResource = resource.getChild('par');
    var resourcePath = "";

    if (rootResource != null) {
        resourcePath = rootResource.getPath();
    } else {
        // if we don't have a "par" subnode just take the first one
        var children = rootResource.getChildren();
        if (children.length > 0) {
            resourcePath = children[0].getPath();
        }
    }
    return {
        cssClasses: "xf-web-container",
        resourcePath: resourcePath,
        rootResource:rootResource,
    };
});
