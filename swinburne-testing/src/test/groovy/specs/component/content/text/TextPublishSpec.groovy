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

        and: "Has sample paraformat-h1"
        assert $("${selector} h1").isEmpty() == false

        and: "Has sample paraformat-h2"
        assert $("${selector} h2").isEmpty() == false

        and: "Has sample paraformat-h3"
        assert $("${selector} h3").isEmpty() == false

        and: "Has sample paraformat-p"
        assert $("${selector} p").isEmpty() == false

        and: "Has sample paraformat-hyperlinks"
        assert $("${selector} a").isEmpty() == false

        and: "Has sample paraformat-ul"
        assert $("${selector} ul").isEmpty() == false

        and: "Has sample paraformat-ol"
        assert $("${selector} ol").isEmpty() == false

        and: "Has sample paraformat-small"
        assert $("${selector} p small").isEmpty() == false

        and: "Has sample table content"
        assert $("${selector} table").isEmpty() == false

        and: "Should have simple BR element"
        assert $("${selector} div br").isEmpty() == false

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

        and: "Has sample paraformat-h1"
        assert $("${selector} h1").isEmpty() == false

        and: "Has sample paraformat-h2"
        assert $("${selector} h2").isEmpty() == false

        and: "Has sample paraformat-h3"
        assert $("${selector} h3").isEmpty() == false

        and: "Has sample paraformat-p"
        assert $("${selector} p").isEmpty() == false

        and: "Has sample paraformat-hyperlinks"
        assert $("${selector} a").isEmpty() == false

        and: "Has sample paraformat-ul"
        assert $("${selector} ul").isEmpty() == false

        and: "Has sample paraformat-ol"
        assert $("${selector} ol").isEmpty() == false

        and: "Has sample paraformat-small"
        assert $("${selector} p small").isEmpty() == false

        and: "Has sample table content"
        assert $("${selector} table").isEmpty() == false

        and: "Should have simple BR element"
        assert $("${selector} div br").isEmpty() == false

        where:
        viewport << getViewPorts()
    }


}
