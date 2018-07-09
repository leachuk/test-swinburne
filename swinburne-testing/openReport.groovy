
try {

    File file = new File("./target/docs/summary.html")

    if(!file.isFile()) {
        //System.err.println("Could not find report file: "+file)
        //return true
    } else {
        def url = "file://${new File("./target/docs/summary.html").canonicalPath}"
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