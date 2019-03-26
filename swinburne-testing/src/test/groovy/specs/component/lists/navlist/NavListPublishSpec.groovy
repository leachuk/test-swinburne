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
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} li").size() == 2

        and: "Has first item"
        assert $("${selector} li").firstElement().getAttribute("textContent").trim().contains("Page1")

        and: "Has last item"
        assert $("${selector} li").lastElement().getAttribute("textContent").trim().contains("Page2")

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
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} li").size() == 2

        and: "Has first item"
        assert $("${selector} .menu a").firstElement().getAttribute("textContent").trim().contains("Page1")

        and: "Has last item"
        assert $("${selector} .menu a").lastElement().getAttribute("textContent").trim().contains("Page2")

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
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} li.dropdown").size() == 2

        and: "Has first item"
        assert $("${selector} li.dropdown").getAt(0).find("a.dropdown-toggle").getAttribute("textContent").trim() == "Page1"

        and: "First item has five sub items"
        assert $("${selector} li.dropdown").getAt(0).find("div.dropdown-menu a").size() == 5

        and: "Has last item"
        assert $("${selector} li.dropdown").getAt(1).find("a.dropdown-toggle").getAttribute("textContent").trim() == "Page2"

        and: "Last item has five sub items"
        assert $("${selector} li.dropdown").getAt(1).find("div.dropdown-menu a").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Nav List: Stacked with Children List has correct current page marks #viewport.label")
    def "Nav List: Stacked with Children List has correct current page marks"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Nav List" > "Pages" > "Page 2" > "Page 2"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#inheritedListInAside"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPageUrl("content/swinburne-showcase/en/component/lists/nav-list/pages/page2/page2.html")

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has five nav items"
        assert $("${selector} li.nav-item").size() == 5

        and: "Has first menu item not marked as current"
        assert !$("${selector} li.nav-item").getAt(0).getAttribute("class").contains("current")

        and: "Has second menu item marked as current"
        assert $("${selector} li.nav-item").getAt(1).getAttribute("class").contains("current")

        and: "Has second menu item with link marked as active"
        assert $("${selector} li.nav-item").getAt(1).find("a.active").size() == 1

        and: "Has second menu item with link marked as active should have current link title equals Page2"
        assert $("${selector} li.nav-item.current").find("a.current").firstElement().getAttribute("textContent").trim() == "Page2"

        where:
        viewport << getViewPorts()

    }

    @Unroll("Nav List: Dropdown with Children List in #viewport.label")
    def "Nav List: Dropdown with Children List"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Nav List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#navlist6"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has five first level items"
        assert $("${selector} li").size() == 5

        and: "First item has five items"
        assert $("[aria-labelledby=navlist6_page1] > a.nav-link").size() == 5

        and: "First item has five items"
        assert $("[aria-labelledby=navlist6_page1_page1] > a.nav-link").size() == 2

        where:
        viewport << getViewPorts()
    }


}
