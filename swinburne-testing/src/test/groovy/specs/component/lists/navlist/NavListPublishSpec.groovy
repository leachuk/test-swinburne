package specs.component.lists.navlist

import spock.lang.IgnoreRest
import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class NavListPublishSpec extends ComponentSpec {

    String pathPage = "component/lists/nav-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/navlist"

    def setupSpec() {
        loginAsAdmin()
    }


    @Unroll("Nav List: Default with Fixed List in #viewport.label")
    def "Nav List: Default with Fixed List"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Nav List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#navlist1"
        def selectorContainer = "#contentblock1 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} li").size() == 2

        and: "Has first item"
        assert $("${selector} li:nth-child(1)").text().trim() == "Page1"

        and: "Has last item"
        assert $("${selector} li:nth-child(2)").text().trim() == "Page2"

        where:
        viewport << getViewPorts()
    }

    @IgnoreRest
    @Unroll("Nav List: Simple with Fixed List in #viewport.label")
    def "Nav List: Simple with Fixed List"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Nav List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#navlist2"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} li").size() == 2

        and: "Has first item"
        assert $("${selector} a:nth-child(1)").text().trim() == "Page1"

//         and: "Has last item"
//         assert $("${selector} li:nth-child(2) a").text().trim() == "Page2"

        where:
        viewport << getViewPorts()
    }


    @Unroll("Nav List: Stacked with Fixed List in #viewport.label")
    def "Nav List: Stacked with Fixed List"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Nav List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#navlist3"
        def selectorContainer = "#contentblock3 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} li .dropdown-toggle").size() == 2

        and: "Has first item"
        assert $("${selector} li:nth-child(1) .dropdown-toggle").text().trim() == "Page1"

        and: "First item has five sub items"
        assert $("${selector} li:nth-child(1) .dropdown-menu a").size() == 2

        and: "Has last item"
        assert $("${selector} li:nth-child(2) .dropdown-toggle").text().trim() == "Page2"

        and: "First item has five sub items"
        assert $("${selector} li:nth-child(2) .dropdown-menu a").size() == 2

        where:
        viewport << getViewPorts()
    }


}
