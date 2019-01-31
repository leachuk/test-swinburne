package specs.styleguide.components.breadcrumbs

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class BreadcrumbsScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/components/content-block"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock_light/"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Breadcrumb on light background"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_light"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of Breadcrumb on dark background"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_dark"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }
}
