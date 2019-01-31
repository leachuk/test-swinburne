package specs.component.layout.breadcrumb

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class BreadcrumbScreenshotSpec extends ComponentSpec {

    String pathPage = "component/layout/breadcrumb"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/breadcrumb"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of breadcrumb in #viewport.label")
    def "Appearance of breadcrumb"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#breadcrumb1"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()


    }

    @Unroll("Appearance of breadcrumb with Changed Start Level in #viewport.label")
    def "Appearance of breadcrumb with Changed Start Level"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#breadcrumb2"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()


    }

    @Unroll("Appearance of breadcrumb with Changed Start Level and End Level in #viewport.label")
    def "Appearance of breadcrumb with Changed Start Level and End Level"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#breadcrumb3"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()


    }

    @Unroll("Appearance of breadcrumb with light styling in #viewport.label")
    def "Appearance of breadcrumb with light styling"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#breadcrumb4"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()


    }

    @Unroll("Appearance of breadcrumb with dark styling in #viewport.label")
    def "Appearance of breadcrumb with dark styling "() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#breadcrumb5"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()


    }

}
