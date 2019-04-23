package specs.styleguide.components.breadcrumbs

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class BreadcrumbsScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/components/breadcrumbs"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par"

    def setupSpec() {
        loginAsAdmin()
    }

	@Unroll("Appearance of Breadcrumb on light background in #viewport.label")
    def "Appearance of Breadcrumb on light background"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_light"

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

	@Unroll("Appearance of Breadcrumb on dark background in #viewport.label")
    def "Appearance of Breadcrumb on dark background"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_dark"

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
