package specs.dls.colour

import spock.lang.IgnoreRest
import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class ColourScreenshotSpec extends ComponentSpec {

    String pathPage = "Styleguide-SWU/guidelines/colour"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/embedsource"


    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Color #viewport.label")
    def "Appearance of Color"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#embedsource_6KCRFO4FQ"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }
}