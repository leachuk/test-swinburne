package specs.component.layout.footer

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class FooterInheritPublishSpec extends ComponentSpec {
    String pathPage = "component/layout/footer/footerinherit"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/footer"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component in #viewport.label")
    def "Functionality of Component"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "Footer" > "Footer Inherit"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#footer1"
        def selectorContainer = "#contentblock1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: 'Should have sample rich text'
        assert $(selector + " .text[component]").text().trim() == "Footer Content"
        takeScreenshot($(selectorContainer).firstElement(), "Should have sample rich text")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component with Background in #viewport.label")
    def "Functionality of Component with Background"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "Footer" > "Footer Inherit"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#footer2"
        def selectorContainer = "#contentblock2"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: 'Should have sample rich text'
        assert $(selector + " .text[component]").text().trim() == "Footer Content with Background"

        and: 'Section should have a background image'
        assert $(selector).css("background-image").contains("/component/layout/footer/_jcr_content")
        takeScreenshot($(selectorContainer).firstElement(), "Should have sample rich text")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component with Style in #viewport.label")
    def "Functionality of Component with Style"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "Footer"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#footer3"
        def selectorContainer = "#contentblock3"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "Should have sample rich text")

//        and: 'Should have class modifier assigned'
//        assert $(selector).attr("class").contains("bg-c8")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Swinburne Global in #viewport.label")
    def "Functionality of Swinburne Global"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "Footer"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#footer_swinburne_global"
        def selectorContainer = "#contentblock4"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have rich text"
        assert $(selector + " #text_AYS4ZU6BL").eq(0).text().trim() == "Connect with Swinburne University"

        and: "Should have Swinburne brand logo"
        assert $(selector + " #embedsource_AP3MMJNLO > img").attr("src").contains("/etc/clientlibs/swinburne/sut/images/swinburne-logo.svg")
        takeScreenshot($(selectorContainer).firstElement(), "Should have Swinburne Global")

        where:
        viewport << getViewPorts()
    }
}
