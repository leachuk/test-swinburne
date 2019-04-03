package specs.styleguide.components.pagedetails

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class DLSPageDetailsScreenshotSpec extends ComponentSpec{

    String pathPage = "styleguide/components/page-details"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/swinburne-showcase/en/Styleguide-SWU/components/page-details/jcr:content/article/par/page_details"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Narrow Version (Page Details) in #viewport.label")
    def "Appearance of Narrow Version (Page Details)"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#page_details_TYYPIP9YT"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()
    }
}
