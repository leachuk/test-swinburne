package specs.component.content.link

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class LinkPublishSpec extends ComponentSpec {

    String pathPage = "component/content/link"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/link"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {

        given: 'Page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component link"
        assert $(selector).text().trim() == "Link: Default"
        report("Should have sample component link")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Variant: Button in #viewport.label")
    def "Functionality of Component Variant: Button"() {

        given: 'Page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link2"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component link"
        assert $(selector).text().trim() == "Link: Button"
        report("Should have sample component link")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Variant: Default no Label in #viewport.label")
    def "Functionality of Component Variant: Default no Label"() {

        given: 'The page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link3"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component link"
        assert $(selector).text().trim() == "Link"
        report("Should have sample component link")

        where:
        viewport << getViewPorts()
    }


}
