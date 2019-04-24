package specs.component.layout.contenttabs

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class ContentTabsPublishSpec extends ComponentSpec {

    String pathPage = "component/layout/contenttabs"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock/par/contenttabs"


    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component with List of Children Pages in #viewport.label")
    def "Functionality of Component with List of Children Pages"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "ContentTabs"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contenttabs1"
        def selectorContainer = "#contentblock1 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()
        takeScreenshot($(selectorContainer).firstElement(), "Should have sample content")

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "First tab should be active"
        assert $(selector + " .nav-link.active").text().trim() == "Tab Page Content 1"

        and: "Tab breadcrumb should match current page"
        assert $(selector + " #page1 .breadcrumb li")[3].text().trim() == "Content Tabs"

        when: "I select second tab"
        $(selector + " .nav.nav-tabs").find("li:nth-child(2) a").click()

        then: "Second tab content show be visible"
        assert $(selector + " .nav-link.active").text().trim() == "Tab Page Content 2"
        takeScreenshot($(selectorContainer).firstElement(), "Second tab content show be visible")

        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component with List of Static Pages in #viewport.label")
    def "Functionality of Component with List of Static Pages"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "ContentTabs"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contenttabs2"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()
        takeScreenshot($(selectorContainer).firstElement(), "I am on the component showcase page")

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "First tab should be active"
        assert $(selector + " .nav-link.active").text().trim() == "Content Block"

        when: "I select second tab"
        $(selector + " .nav.nav-tabs").find("li:nth-child(2) a").click()

        then: "Second tab content show be visible"
        assert $(selector + " .nav-link.active").text().trim() == "Content Block Lock"
        takeScreenshot($(selectorContainer).firstElement(), "Second tab content show be visible")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Empty in #viewport.label")
    def "Functionality of Component Empty"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "ContentTabs"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contenttabs3"
        def selectorContainer = "#contentblock3 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()
        takeScreenshot($(selectorContainer).firstElement(), "I am on the component showcase page")

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: 'Should have sample rich text'
        assert $(selector + " .nav-link.active").text().trim() == "Empty"

        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component Variant: Render with List of Children Pages in #viewport.label")
    def "Functionality of Component Variant: Render with List of Children Pages"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "ContentTabs"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contenttabs4"
        def selectorContainer = "#contentblock4 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()
        takeScreenshot($(selectorContainer).firstElement(), "I am on the component showcase page")

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "First tab should be active"
        assert $(selector + " .nav-link.active").text().trim() == "Tab Page Content 1"

        and: "Tab breadcrumb should match page being included"
        assert $(selector + " #page1 .breadcrumb li")[4].text().trim() == "Tab Page Content 1"

        when: "I select second tab"
        $(selector + " .nav.nav-tabs").find("li:nth-child(2) a").click()

        then: "Second tab content show be visible"
        assert $(selector + " .nav-link.active").text().trim() == "Tab Page Content 2"
        takeScreenshot($(selectorContainer).firstElement(), "Second tab content show be visible")

        where:
        viewport << getViewPorts()
    }

}
