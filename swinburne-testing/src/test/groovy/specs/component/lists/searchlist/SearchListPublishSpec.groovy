package specs.component.lists.searchlist

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class SearchListPublishSpec extends ComponentSpec {

    String pathPage = "component/lists/search-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock2/par/searchlist"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default without Query in #viewport.label")
    def "Functionality of Component Variant: Default without Query"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Search List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#searchlist1"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Should be empty"
        assert $(selector).children().size() == 0

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Default with Query in #viewport.label")
    def "Functionality of Component Default with Query"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Search List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#searchlist1"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPageWithQuery(language, "q=city")

        printDebug("URL", driver.currentUrl)

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Should contain results"
        assert $(selector).find(".results").children().size() > 0

        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component as Cards with Query in #viewport.label")
    def "Functionality of Component as Cards with Query"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Search List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#searchlist2"
        def selectorContainer = "#contentblock3 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPageWithQuery(language, "q=city")

        printDebug("URL", driver.currentUrl)

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Should contain results"
        assert $(selector).find(".results").children().size() > 0

        and: "Results should in card format"
        assert $(selector).find(".results").find(".card").children().size() > 0

        where:
        viewport << getViewPorts()
    }


}
