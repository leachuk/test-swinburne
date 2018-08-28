package specs.component.content.embedsource

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class EmbedSourceSsiScreenshotSpec extends ComponentSpec {

    String pathPage = "component/content/embedsource-ssi"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/embedsource-ssi"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Component Variant: Default with Parameters in #viewport.label")
    def "Appearance of Component Variant: Default with Parameters"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#embedsource-ssi1"

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
