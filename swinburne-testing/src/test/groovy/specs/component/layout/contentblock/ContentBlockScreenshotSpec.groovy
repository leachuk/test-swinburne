package specs.component.layout.contentblock

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class ContentBlockScreenshotSpec extends ComponentSpec {

    String pathPage = "component/layout/contentblock"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Component in #viewport.label")
    def "Appearance of Component"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock1"

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

    @Unroll("Appearance of Component with Background in #viewport.label")
    def "Appearance of Component with Background"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock9"

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

    @Unroll("Appearance of Component with Background Video in #viewport.label")
    def "Appearance of Component with Background Video"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock10"

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

}
