package specs.component.content.pagetitle

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class PageTitlePublishSpec extends ComponentSpec {

    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String pathPage = "component/content/pagetitle"
    String componentPath = "jcr:content/article/par/contentblock1/par/pagetitle"


    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component: Default"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Page Title"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagetitle1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample text"
        assert $(selector).text().trim().equalsIgnoreCase("Page Title")
        report("Should have sample rich text")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component: Override Title in #viewport.label")
    def "Functionality of Component: Override Title"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Page Title"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagetitle2"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample text"
        assert $(selector).text().trim().equalsIgnoreCase("Override of Page Title")
        report("Should have sample text")

        where:
        viewport << getViewPorts()
    }


}
