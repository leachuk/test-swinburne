package specs.styleguide.guidelines.template

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class TemplateScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/guidelines/template"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/"

    def setupSpec() {
        loginAsAdmin()
    }


    def "Appearance of Template Description"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock1"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }
}
