package specs.component.content.pagedescription

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class PageDescriptionPublishSpec extends ComponentSpec {

    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String pathPage = "component/content/pagedescription"
    String componentPath = "jcr:content/article/par/contentblock1/par/pagedescription"


    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component: Default"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Page Properties Description"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagedescription1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample text"
        assert $(selector).text().trim().equalsIgnoreCase("Page Properties Description")
        report("Should have sample rich text")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component with Override in #viewport.label")
    def "Functionality of Component with Override"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Page Description"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagedescription2"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample text"
        assert $(selector).text().trim().equalsIgnoreCase("Override of Page Description")
        report("Should have sample text")

        where:
        viewport << getViewPorts()
    }


}
