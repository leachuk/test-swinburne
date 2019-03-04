package specs.styleguide.components.pagelist


import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class DLSPageListPublishSpec extends ComponentSpec {
    String pathPage = "styleguide/components/page-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Page List: Card with Image, Tag, Title and Action - Promoted (default background) in #viewport.label")
    def "Page List: Card with Image, Tag, Title and Action - Promoted (default background)"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "reference_OXHFFXJJE #pagelist36"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has 4 list items"
        //todo: this will be refactored when we change from using content-reference. It currently pulls in many as the references are to the same pagelist
        assert $("${selector} > div > ul > li").size() == 4

        and: "First card has yellow background colour when default background"
        assert $("${selector} > div > ul > li.first > div > div.card-body").css("background-color") == "#ffeb14"

        where:
        viewport << getViewPorts()
    }

}
