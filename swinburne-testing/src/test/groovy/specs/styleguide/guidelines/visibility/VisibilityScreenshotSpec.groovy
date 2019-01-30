package specs.styleguide.guidelines.visibility

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class VisibilityScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/guidelines/visibility"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/embedsource"

    def setupSpec() {
        loginAsAdmin()
    }


    def "Appearance of Responsive Modifiers Table"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#responsive-modifiers"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }
}
