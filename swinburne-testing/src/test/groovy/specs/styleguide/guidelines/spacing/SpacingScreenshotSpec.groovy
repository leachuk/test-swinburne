package specs.styleguide.guidelines.spacing


import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class SpacingScreenshotSpec extends ComponentSpec {

    String pathPage = "Styleguide-SWU/guidelines/spacing-units"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/embedsource"

    def setupSpec() {
        loginAsAdmin()
    }


    def "Appearance of Spacing Table"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#embedsource1"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }
}
