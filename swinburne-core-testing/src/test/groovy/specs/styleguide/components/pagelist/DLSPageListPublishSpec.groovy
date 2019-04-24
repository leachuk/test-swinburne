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
        def selector = "#reference_OXHFFXJJE #pagelist_36a"
        //def selector = "#reference_OXHFFXJJE #pagelist36"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has 4 list items"
        assert $("${selector} > div > ul > li").size() == 4

        and: "First card has yellow background colour when default background"
        assert $("${selector} > div > ul > li.first > div > div.card-body").css("background-color") == "rgba(255, 235, 20, 1)" //#ffeb14

        and: "last card has white background colour when default background"
        assert $("${selector} > div > ul > li.last > div > div.card-body").css("background-color") == "rgba(255, 255, 255, 1)" //#f

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Card with Image, Tag, Title and Action - Promoted (default background with charcoal theme) in #viewport.label")
    def "Page List: Card with Image, Tag, Title and Action - Promoted (default background with charcoal theme)"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference_1XHFFXJJE #pagelist_36b"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has 4 list items"
        assert $("${selector} > div > ul > li").size() == 4

        and: "First card has yellow background colour when default background"
        assert $("${selector} > div > ul > li.first > div > div.card-body").css("background-color") == "rgba(255, 235, 20, 1)" //#ffeb14

        and: "Last card has charcoal background colour when default background"
        assert $("${selector} > div > ul > li.last > div > div.card-body").css("background-color") == "rgba(36, 35, 35, 1)" //#242323

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Card with Image, Tag, Title and Action - Promoted (grey background) in #viewport.label")
    def "Page List: Card with Image, Tag, Title and Action - Promoted (grey background)"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference_2XHFFXJJE #pagelist_36a"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has 4 list items"
        assert $("${selector} > div > ul > li").size() == 4

        and: "First card has charcoal background colour when default background"
        assert $("${selector} > div > ul > li.first > div > div.card-body").css("background-color") == "rgba(36, 35, 35, 1)" //#242323

        and: "Last card has white background colour when default background"
        assert $("${selector} > div > ul > li.last > div > div.card-body").css("background-color") == "rgba(255, 255, 255, 1)" //#f

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Card with Image, Tag, Title and Action - Promoted (yellow background) in #viewport.label")
    def "Page List: Card with Image, Tag, Title and Action - Promoted (yellow background)"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference_4XHFFXJJE #pagelist_36a"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has 4 list items"
        assert $("${selector} > div > ul > li").size() == 4

        and: "First card has charcoal background colour when default background"
        assert $("${selector} > div > ul > li.first > div > div.card-body").css("background-color") == "rgba(36, 35, 35, 1)" //#242323

        and: "Last card has white background colour when default background"
        assert $("${selector} > div > ul > li.last > div > div.card-body").css("background-color") == "rgba(255, 255, 255, 1)" //#fff

        where:
        viewport << getViewPorts()
    }

}
