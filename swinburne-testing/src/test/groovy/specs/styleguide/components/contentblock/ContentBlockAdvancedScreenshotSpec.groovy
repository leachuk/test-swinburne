package specs.styleguide.components.contentblock

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class ContentBlockAdvancedScreenshotSpec extends ComponentSpec {

    String pathPage = "Styleguide-SWU/components/content-block"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock_1045719959/par/contentblock"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Title"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_advanced"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }
}
