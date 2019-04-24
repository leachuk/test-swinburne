package specs.component.layout.aside

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class AsidePublishSpec extends ComponentSpec {

    String pathPage = "component/layout/aside"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/aside"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component in #viewport.label")
    def "Functionality of Component"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "Aside"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#plainaside"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: 'Should have sample rich text'
        assert $(selector + " .text[component]").text().trim() == "Aside Content"
        report("Should have sample rich text")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component with Background in #viewport.label")
    def "Functionality of Component with Background"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "Aside"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#asidewithbackground"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()
        report("I am on the component showcase page")

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: 'Should have sample rich text'
        assert $(selector + " .text[component]").text().trim() == "Aside Content with Background"

        and: 'Section should have a background image'
        assert $(selector).css("background-image").indexOf("/${pathSite}/${language}/${pathPage}/_jcr_content/article/par/contentblock2/par/aside/bgimage.img.png/0.png") > 0

        where:
        viewport << getViewPorts()
    }


}
