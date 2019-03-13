package specs.styleguide.guidelines.logo

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class LogoScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/guidelines/logo-and-usage"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of logo in #viewport.label")
    def "Appearance of logo"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#logo_embedsource"

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
