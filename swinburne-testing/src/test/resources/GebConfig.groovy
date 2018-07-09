import com.google.common.io.Files
import geb.driver.BrowserStackDriverFactory
import geb.driver.SauceLabsDriverFactory
import groovy.json.JsonOutput
import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.safari.SafariDriver
import support.ReportListener

import java.util.logging.Level

import static org.apache.commons.lang3.SystemUtils.*
//find and select driver bin to use
File findDriverExecutable(String named) {

    new File("drivers").listFiles().findAll {
        !it.name.endsWith(".version")
    }.find {
        if (IS_OS_LINUX) {
            it.name.contains(named + "-linux")
        } else if (IS_OS_WINDOWS) {
            it.name.contains(named + "-windows")
        } else if (IS_OS_MAC) {
            it.name.contains(named + "-mac")
        }
    }

}



boolean is64bit = System.getProperty("sun.arch.data.model").contains("64")

reportingListener = new ReportListener()

String global_scheme = System.properties.getProperty("crx.scheme","http")
String global_host = System.properties.getProperty("crx.host","192.168.27.2")
String global_port = System.properties.getProperty("crx.port","4502")
String global_username = System.properties.getProperty("crx.user","admin")
String global_password = System.properties.getProperty("crx.password","admin")
String global_url = "${global_scheme}://${global_host}:${global_port}"
String global_authoruimode = System.properties.getProperty("crx.authoruimode","CLASSIC")

printDebug("SETTINGS",[ global_host,global_port,global_username,'*'.multiply(global_password.length())])

System.properties.setProperty("geb.build.baseUrl", global_url)

printDebug("SETTINGS URL", global_url)

String gebEnv = System.getProperty("geb.env")
printDebug("SETTINGS geb.env", gebEnv)

if ( gebEnv == "" ) {
    System.setProperty("geb.env","local-chrome")
}


printDebug("SETTINGS driver", findDriverExecutable("chromedriver").canonicalPath)

//specific driver
environments {

    "local-styleguide" {
        printDebug("DRIVER", "local-styleguide")
        String styleguideHost = System.properties.getProperty("styleguideHost","192.168.27.2")
        String styleguidePort = System.properties.getProperty("styleguidePort","80")

        printDebug("SETTINGS URL", "http://${styleguideHost}:${styleguidePort}")

        System.properties.setProperty("geb.build.baseUrl", "http://${styleguideHost}:${styleguidePort}")
    }

    // run as -Dgeb.env=chrome
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    "local-chrome" {
        driver = {

            printDebug("DRIVER", "local-chrome")

            String driverPath = findDriverExecutable("chromedriver").canonicalPath

            printDebug("DRIVER PATH", driverPath)

            if (driverPath.isEmpty()) {
                printDebug("CANT FIND DRIVER", driverPath)
                return
            }

            System.setProperty("webdriver.chrome.driver",driverPath)

//            printDebug("SETTINGS URL1", System.getProperty("webdriver.gecko.driver"))

            // HashMap<String, Object> chromePrefs = new HashMap<>()
            // chromePrefs.put("profile.default_content_settings.popups", 0)
            // chromePrefs.put("download.default_directory", System.getProperty("user.dir") + TestProperties.getInstance().getProperties().getProperty("chrome.file.save.dir"))
            // options.setExperimentalOption("prefs", chromePrefs)

            ChromeOptions options = new ChromeOptions()
            options.addArguments(Arrays.asList(
                    "--headless",
                    "--enable-automation",
                    "--test-type",
                    "--start-maximized",
                    "--disable-web-security",
                    "--allow-file-access-from-files",
                    "--allow-running-insecure-content",
                    "--allow-cross-origin-auth-prompt",
                    "--allow-file-access",
                    "--no-sandbox",
                    "--ignore-certificate-errors",
                    "--homepage=about:blank",
                    "--no-first-run",
                    "--disable-infobars",
                    "--disable-notifications",
                    "--disable-extensions",
                    "--profile-directory=Default",
                    "--user-data-dir=target/chrome-user-data",
                    "--use-fake-ui-for-media-stream=1",
                    "--disable-popup-blocking",
                    "--disable-gpu"

                )
            );

            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_settings.popups", 0);
            options.setExperimentalOption("prefs", prefs);

            List<String> excludeSwitches = new ArrayList<String>();
            excludeSwitches.add("disable-component-update");
            options.setExperimentalOption('excludeSwitches', excludeSwitches)

            LoggingPreferences logPrefs = new LoggingPreferences()
            logPrefs.enable(LogType.BROWSER, Level.ALL)

            DesiredCapabilities capabilities = DesiredCapabilities.chrome()
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
            capabilities.setCapability(ChromeOptions.CAPABILITY, options)
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)

            return new ChromeDriver(capabilities)

        }
    }

    // run as -Dgeb.env=firefox
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    "local-firefox" {
        driver = {
            printDebug("DRIVER", "local-firefox")


            String extention = ""
            String driverPath = ""
            if (SystemUtils.IS_OS_WINDOWS) {
                extention = ".exe"
            }

            driverPath = System.getProperty("user.dir")
                    .concat("/drivers/geckodriver-")
                    .concat(System.properties['os.name'].toLowerCase().split()[0])
                    .concat("-")
                    .concat(is64bit?"64":"32")
                    .concat("bit")
                    .concat(extention)

            printDebug("DRIVER PATH", driverPath)

            System.setProperty("webdriver.gecko.driver",driverPath)

            printDebug("SETTINGS URL", System.getProperty("webdriver.gecko.driver"))

            def profileDir = Files.createTempDir()

            // disable flash
            def profile = new FirefoxProfile(profileDir)
            profile.setPreference("plugin.state.flash", 0)
            profile.setPreference("gfx.font_rendering.graphite.enabled", false)

            DesiredCapabilities capabilities = DesiredCapabilities.firefox()
            capabilities.setCapability(CapabilityType.PROXY, proxy)
            capabilities.setCapability(FirefoxDriver.PROFILE, profile)
            return new FirefoxDriver(capabilities);
        }
    }

    // run as -Dgeb.env=safari
    "local-safari" {
        driver = {
            printDebug("DRIVER", "local-safari")
            return new SafariDriver()
        }
    }

    // run as  -Dgeb.env=phantomjs
    "local-phantomjs" {
        driver = {
            printDebug("DRIVER", "local-phantomjs")
//            DesiredCapabilities capabilities = new DesiredCapabilities()
//            capabilities.setJavascriptEnabled(true)

            DesiredCapabilities capabilities = DesiredCapabilities.phantomjs()

            def phantomBin = "which phantomjs".execute().text.trim()
            def phantom2Bin = "which phantomjs2".execute().text.trim()

            if (!phantomBin && phantom2Bin) {
                phantomBin = phantom2Bin
            }

            printDebug("DRIVER BIN", phantomBin)

            capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,phantomBin)
            capabilities.setJavascriptEnabled(true)
//
            capabilities.setCapability("javascriptEnabled",true)
            capabilities.setCapability("browserConnectionEnabled",true)
            capabilities.setCapability("localToRemoteUrlAccessEnabled",true)
            capabilities.setCapability("acceptSslCerts",true)
            capabilities.setCapability("webSecurityEnabled",false)
            capabilities.setCapability("applicationCacheEnabled",true)
            capabilities.setCapability("takesScreenshot",true)
            capabilities.setCapability("cssSelectorsEnabled",true)
            capabilities.setCapability("nativeEvents",true)
            capabilities.setCapability("handlesAlerts",false)
            capabilities.setCapability("databaseEnabled",false)
            capabilities.setCapability("webStorageEnabled",false)
            capabilities.setCapability("rotatable",true)
            capabilities.setCapability("locationContextEnabled",false)

            phantomArgs = ['--webdriver-loglevel=INFO', '--web-security=false', '--ignore-ssl-errors=true', '--ssl-protocol=any']
            phantomArgs2 = ["--logLevel=INFO", "--logColor=true"]

//        capabilities.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:16.0) Gecko/20121026 Firefox/16.0");


            capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
            capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, phantomArgs2);

            LoggingPreferences loggingprefs = new LoggingPreferences();
            loggingprefs.enable(LogType.BROWSER, Level.ALL);

            capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);

            def driver = new PhantomJSDriver(capabilities)

//            driver.setLogLevel(Level.OFF)
            driver.manage().window().setSize(new Dimension(1920, 1080))
            System.out.println(driver.getCapabilities());
            return driver
        }
    }

    // run as  -Dgeb.env=htmlunit
    "local-htmlunit" {
        printDebug("DRIVER", "local-htmlunit")

        driver = { new HtmlUnitDriver() }
    }

    // run as  -Dgeb.env=htmlunit
    "remote-browserstack" {
        printDebug("DRIVER", "local-browserstack")

        def browserStackBrowser = System.getProperty("geb.browserstack.browser")
        if (browserStackBrowser) {
            driver = {
                def username = System.getenv("GEB_BROWSERSTACK_USERNAME")
                assert username
                def accessKey = System.getenv("GEB_BROWSERSTACK_AUTHKEY")
                assert accessKey
                new BrowserStackDriverFactory().create(browserStackBrowser, username, accessKey)
//                def localId = System.getenv("GEB_BROWSERSTACK_LOCALID")
//                assert localId
//                new BrowserStackDriverFactory().create(browserStackBrowser, username, accessKey, localId)
            }
        }
    }

    // run as  -Dgeb.env=remote
    "remote-saucelabs" {
        printDebug("DRIVER", "local-saucelabs")

        def sauceLabsBrowser = System.getProperty("geb.saucelabs.browser")
        if (sauceLabsBrowser) {
            driver = {
                def username = System.getenv("GEB_SAUCE_LABS_USER")
                assert username
                def accessKey = System.getenv("GEB_SAUCE_LABS_ACCESS_PASSWORD")
                assert accessKey
                new SauceLabsDriverFactory().create(sauceLabsBrowser, username, accessKey)
            }
        }
    }

    "remote-seleniumhub" {
        driver = {
            printDebug("DRIVER", "local-seleniumhub")
            DesiredCapabilities capabilities = DesiredCapabilities.phantomjs()
            capabilities.setBrowserName("phantomjs")
            URL url = new URL("http://127.0.0.1:4444/wd/hub")
            return new RemoteWebDriver(url, capabilities);
        }
    }

    "local-htmlunit" {
        driver = {
            printDebug("DRIVER", "local-htmlunit")
            DesiredCapabilities capabilities = new DesiredCapabilities().htmlUnitWithJs()
            capabilities.setJavascriptEnabled(true)
            def driver = new HtmlUnitDriver(capabilities)
            return driver
        }
    }

}

waiting {
    timeout = 15
    retryInterval = 0.5
    presets {
        aBit {
            timeout = 15
            retryInterval = 2
        }
        slow {
            // Good for specs checking url rewrites (which refreshes every minute)
            timeout = 120
            retryInterval = 1
        }
    }
}


static printDebug(String name, Object values) {
    def json = JsonOutput.toJson(["${name}": values])

    System.out.println(json.toString())
}
