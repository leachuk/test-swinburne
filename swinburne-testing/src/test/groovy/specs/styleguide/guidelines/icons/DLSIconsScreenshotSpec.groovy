package specs.styleguide.guidelines.icons

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class DLSIconsScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/guidelines/iconography"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock_1460944026/par/embedsource"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Iconography"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)
    }
}
