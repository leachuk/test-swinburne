package specs.styleguide.components.contentblock

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class ContentBlockStyleguideScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/components/content-block"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock_2/par/contentblock"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Advanced Content Block with Title in #viewport.label")
    def "Appearance of Advanced Content Block with Title"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "contentblock_2"

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
