package specs.component.content.text

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class TextPublishSpec extends ComponentSpec {

    String pathPage = "component/content/text"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/text"


    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Text"'
        def selector = "#text1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample rich text"
        assert $(selector).text().trim().startsWith("Heading 1")
        report("Should have sample rich text")

        and: "Has sample table content"
        assert $("${selector} table").isEmpty() == false

        and: "Has sample link"
        assert $("${selector} a").isEmpty() == false

        and: "Should have simple BR element"
        assert $("$selector div br").size() > 0

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Variant: Simple in #viewport.label")
    def "Functionality of Component Variant: Simple"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Text"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#text2"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample rich text"
        assert $(selector).text().trim().startsWith("Heading 1")
        report("Should have sample rich text")

        and: "Has sample table content"
        assert $("${selector} table").isEmpty() == false

        and: "Has sample link"
        assert $("${selector} a").isEmpty() == false


        where:
        viewport << getViewPorts()
    }


}
