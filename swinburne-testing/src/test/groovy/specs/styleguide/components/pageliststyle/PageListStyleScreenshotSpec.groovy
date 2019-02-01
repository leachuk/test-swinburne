package specs.styleguide.components.pageliststyle

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class PageListStyleScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/components/page-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Page List with variant 'Card with icon'"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#social-links"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

}
