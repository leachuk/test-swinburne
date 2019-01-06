package specs.component.content.reference

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class ReferencePublishSpec extends ComponentSpec {

    String pathPage = "component/content/reference"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/reference"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Reference"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference1"
        def selectorContainer = "#contentblock1 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample content"
        $("${selector} .page-details").text().trim() == "Content Reference"
        takeScreenshot($(selectorContainer).firstElement(), "Should have sample content")

        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component Variant: Render in #viewport.label")
    def "Functionality of Component Variant: Render"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Reference"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference2"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample content"
        $("${selector} .page-details").text().trim() == "Reference Page Content 1"
        takeScreenshot($(selectorContainer).firstElement(), "Should have sample content")

        where:
        viewport << getViewPorts()
    }


}
