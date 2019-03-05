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

    @Unroll("Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (default background)' in #viewport.label")
    def "Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (default background)'"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference_OXHFFXJJE #pagelist36"

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

    @Unroll("Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (default background with charcoal theme)' in #viewport.label")
    def "Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (default background with charcoal theme)'"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference_1XHFFXJJE #pagelistxxx"

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

    @Unroll("Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (grey background)' in #viewport.label")
    def "Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (grey background)'"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference_2XHFFXJJE #pagelist36"

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

    @Unroll("Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (yellow background)' in #viewport.label")
    def "Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (yellow background)'"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference_4XHFFXJJE #pagelist36"

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

    @Unroll("Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (grey background with charcoal theme)' in #viewport.label")
    def "Appearance of Page List with badge 'Card with Image, Tag, Title and Action - Promoted (grey background with charcoal theme)'"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference_5XHFFXJJE #pagelistxxx"

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

    @Unroll("Appearance of Page List with badge 'Default : Horizontal links' in #viewport.label")
    def "Appearance of Page List with badge 'Default : Horizontal links'"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#reference_COQK8HAL9 #pagelist_F56DNYW78"

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
