package specs.component.details.contactdetails

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class ContactDetailsScreenshotSpec extends ComponentSpec {

    String pathPage = "component/details/contact-details"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/contactdetails"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Component variant Default without included components in #viewport.label")
    def "Appearance of Component variant Default without included components"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contact-details1"

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

    @Unroll("Appearance of Component variant Default Blank without included components in #viewport.label")
    def "Appearance of Component variant Default Blank without included components"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contact-details2"

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
