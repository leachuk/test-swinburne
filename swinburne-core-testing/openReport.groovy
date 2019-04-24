try {
    String reportDir = System.properties.getProperty("project.buildDirectory","target")
    File file = new File("./$reportDir/generated-docs/summary.html")

    System.println("Opening report: "+file.getPath())

    if(!file.isFile()) {
        //System.err.println("Could not find report file: "+file)
        //return true
    } else {
        def url = "file://${new File("./$reportDir/generated-docs/summary.html").canonicalPath}"
        System.println("Opening report : "+url)
        if (!System.properties.containsKey("skipOpenReport")) {
            java.awt.Desktop.desktop.browse url.toURI()
        }
    }




} catch(Throwable t) {
    t.printStackTrace()
    return false
}

return true;
