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

    @Unroll("Experience Page Screenshot in #viewport.label")
    def "Experience Page Screenshot"() {

        given: '>the page hierarchy is created as "Pages" > "SWU" > "Content Page"'
        and: '>I am in the content page showcase page'
        def prefix = "contentpage-"
        def selector = "body"
        def selectorCarousel = "#contentblock_XLRZRS9UE .owl-carousel.owl-loaded"

        when: "I am on the content page showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The page should have finished loading"
        def component = waitForComponent(selector)
        waitFor(15, 0.1) {
            $(selectorCarousel).isDisplayed()
        }

        then: 'It should match the small viewport reference image.'
        designRefFull(selector, prefix)

        where:
        viewport << getViewPorts()
    }
}