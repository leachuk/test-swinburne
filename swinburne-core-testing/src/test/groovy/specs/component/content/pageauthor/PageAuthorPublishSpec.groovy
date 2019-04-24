package specs.component.content.pageauthor

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class PageAuthorPublishSpec extends ComponentSpec {

    String pathPage = "component/content/pageauthor"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/pageauthor"


    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Page Author"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pageauthor1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample text"
        assert $(selector).text().trim().startsWith("Author placeholder content")
        report("Should have sample text")

        where:
        viewport << getViewPorts()
    }
}
