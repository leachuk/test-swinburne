package specs.component.content.link

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class LinkScreenshotSpec extends ComponentSpec {

    String pathPage = "component/content/link"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/link"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Component Variant Default in #viewport.label")
    def "Appearance of Component Variant Default"() {

        given: '>Component has already been added to component showcase page'
        def selector = "#contentblock1 #link1"

        when: 'I am on the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }

    @Unroll("Appearance of Component Variant Button in #viewport.label")
    def "Appearance of Component Variant Button"() {

        given: '>Component has already been added to component showcase page'
        def selector = "#contentblock2 #link2"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }

    @Unroll("Appearance of Component Variant Default no Label in #viewport.label")
    def "Appearance of Component Variant Default no Label"() {

        given: '>Component has already been added to component showcase page'
        def selector = "#contentblock3 #link3"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }

    @Unroll("Appearance of Component Variant Prototype Example in #viewport.label")
    def "Appearance of Component Variant Prototype Example"() {

        given: '>Component has already been added to component showcase page'
        def selector = "#contentblock4 .content"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }

    @Unroll("Appearance of Component Variant Button with icon on left in #viewport.label")
    def "Appearance of Component Variant Button with icon on left"() {

        given: '>Component has already been added to component showcase page'
        def selector = "#contentblock8 #link8"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }

    @Unroll("Appearance of Component Variant Button with icon on right in #viewport.label")
    def "Appearance of Component Variant Button with icon on right"() {

        given: '>Component has already been added to component showcase page'
        def selector = "#contentblock9 #link9"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }

}
