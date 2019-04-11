package specs.uniquepages.contentpage

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class ContentPageScreenshotSpec extends ComponentSpec {

    String pathPage = "pages/swu/content-page"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/page_details"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Global Header Nav on Content Page in #viewport.label")
    def "Appearance of Global Header Nav on Content Page "() {

        given: '>the page hierarchy is created as "Pages" > "SWU" > "Content Page"'
        and: '>I am on the content page'
        def selector = "#header_nav_root"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        js.exec("\$('${selector}').css('position', 'relative'); return true;") //necessary to overcome SM and MD fixed positions which prevented geb locating the header
        designRef(selector)

        where:
        viewport << getViewPorts()
    }

    @Unroll("Appearance of Narrow Banner on Content Page in #viewport.label")
    def "Appearance of Narrow Banner on Content Page "() {

        given: '>the page hierarchy is created as "Pages" > "SWU" > "Content Page"'
        and: '>I am on the content page'
        def selector = "#page_details_NEZHZ1ZX0"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()
    }
}