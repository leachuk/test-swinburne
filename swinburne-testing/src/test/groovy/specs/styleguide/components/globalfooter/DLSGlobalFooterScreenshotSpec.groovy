package specs.styleguide.components.globalfooter

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class DLSGlobalFooterScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/components/global-footer"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock/par/reference"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Swinburne Global Footer in #viewport.label")
    def "Appearance of Swinburne Global Footer"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#footer_swinburne_global"

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
