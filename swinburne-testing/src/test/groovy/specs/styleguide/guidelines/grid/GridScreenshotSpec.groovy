package specs.styleguide.guidelines.grid

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class GridScreenshotSpec extends ComponentSpec {

    String pathPage = "Styleguide-SWU/guidelines/grid"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/embed-source-grid"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of 4 column grid"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#4-columns-grid"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }
}
