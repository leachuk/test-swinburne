package specs.component.widgets.onlinemedia

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class OnlineMediaPublishSpec extends ComponentSpec {

    String pathPage = "component/widgets/online-media"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock/par/onlinemedia"

    def setupSpec() {
        loginAsAdmin()
    }


    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {

        given: '>the page hierarchy is created as "Components" > "Widgets" > "OnlineMedia"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#onlinemedia1"
        def selectorContainer = "#contentblock1 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        report("The component should be on the page")

        and: "Should have sample content"
        assert $("${selector} iframe").attr("src").contains("/embed/tL46xeIV5mc")
        takeScreenshot($(selectorContainer).firstElement(), "Should have sample content")

        where: "Browser size width: #viewport.width and height: #viewport.height"
        viewport << getViewPorts()
    }

    @Unroll("Online Media: Functionality of Component Variant: Video IFrame in #viewport.label")
    def "Functionality of Component Variant: Video IFrame"() {

        given: '>the page hierarchy is created as "Components" > "Widgets" > "OnlineMedia"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#onlinemedia2"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        report("The component should be on the page")

        and: "Should have sample content"
        assert $("${selector} iframe").attr("src").contains("/video/204029471")
        takeScreenshot($(selectorContainer).firstElement(), "Should have sample content")

        and: "IFrame should have VideoObject as itemtype"
        assert $("${selector} iframe").attr("itemtype").endsWith("VideoObject")

        and: "IFrame should have set width"
        assert $("${selector} iframe").attr("width").toInteger() == 400

        and: "IFrame should have set height"
        assert $("${selector} iframe").attr("height").toInteger() == 300

        and: "IFrame should have allowfullscreen"
        assert $("${selector} iframe").attr("allowfullscreen").empty

        where: "Browser size width: #viewport.width and height: #viewport.height"
        viewport << getViewPorts()
    }
}
