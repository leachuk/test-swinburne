package specs.styleguide.components.pagelist

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class DLSPageListScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/components/page-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Page List with badge 'Icon' in #viewport.label")
    def "Appearance of Page List with badge 'Icon'"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#social-links"

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

    @Unroll("Appearance of Page List with badge 'Card with Image, Tag, Title and Action' in #viewport.label")
    def "Appearance of Page List with badge 'Card with Image, Tag, Title and Action'"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist36"

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

    @Unroll("Appearance of Page List with horizontal list in #viewport.label")
    def "Appearance of Page List with horizontal list"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist_F56DNYW78"

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
