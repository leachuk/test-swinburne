package specs.component.lists.pagelist

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class PageListPublishSpec extends ComponentSpec {

    String pathPage = "component/lists/page-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/pagelist"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Page List: Default variant using Default badge in #viewport.label")
    def "Page List: Default variant using Default badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist1"
        def selectorContainer = "#contentblock1 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        and: "Has first item"
        assert $("${selector} li.first").text().trim() == "Page1"

        and: "Has last item"
        assert $("${selector} li.last").text().trim() == "Page5"

        and: "Has three plain items"
        assert $("${selector} li.item").size() == 3

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Default variant using Icon badge in #viewport.label")
    def "Page List: Default variant using Icon badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist2"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 3

        and: "Has icon as contents"
        assert $("${selector} li i").size() == 3

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Default variant using Image badge in #viewport.label")
    def "Page List: Default variant using Image badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist3"
        def selectorContainer = "#contentblock3 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        and: "Has image as contents"
        assert $("${selector} li img").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Default variant using Card badge in #viewport.label")
    def "Page List: Default variant using Card badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist4"
        def selectorContainer = "#contentblock4 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        and: "Has card as contents"
        assert $("${selector} li .card").size() == 5

        where:
        viewport << getViewPorts()
    }


    @Unroll("Page List: Default variant using Horizontal badge in #viewport.label")
    def "Page List: Default variant using Horizontal badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist5"
        def selectorContainer = "#contentblock5 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 3

        and: "Has card as contents"
        assert $("${selector} li .card").size() == 3

        where:
        viewport << getViewPorts()
    }


    @Unroll("Page List: Default Empty in #viewport.label")
    def "Page List: Default Empty"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist6"
        def selectorContainer = "#contentblock6 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has no content"
        assert $("${selector} .content > child").isEmpty() == true

        where:
        viewport << getViewPorts()
    }


    @Unroll("Badge: Card with Image, Title, Description and Action in #viewport.label")
    def "Badge: Card with Image, Title, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist29"
        def selectorContainer = "#contentblock29 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} li").size() == 2

        and: "Has image in first item"
        assert $("${selector} > div > ul > li.first > div > div.card-img-top > img").attr("alt") == "Page1"

        and: "Has title in first item"
        assert $("${selector} > div > ul > li.first > div > div.card-body > h3").text() == "Page1"

        and: "Has description in first item"
        assert $("${selector} > div > ul > li.first > div > div.card-body > p").text() == "Page with Licensed Page Image, with non-Licensed Secondary Image and with Background non-Licensed Image"

        and: "Has call to action in first item"
        assert $("${selector} > div > ul > li.first > div > div.card-body > div > a").text().toUpperCase() == "BUTTON TEXT"

        where:
        viewport << getViewPorts()
    }


}
