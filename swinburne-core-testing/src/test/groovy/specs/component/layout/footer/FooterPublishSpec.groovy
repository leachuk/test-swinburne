package specs.component.layout.footer

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class FooterPublishSpec extends ComponentSpec {

    String pathPage = "component/layout/footer"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Swinburne Global in #viewport.label")
    def "Functionality of Swinburne Global"() {

        given: '>the page hierarchy is created as "Components" > "Layout" > "Footer"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#swinburne_global_footer_inherit"
        def selectorContainer = "#swinburne_global_footer"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        waitForComponent(selector)

        and: "Should have rich text"
        assert $(selector + " #text_AYS4ZU6BL").eq(0).text().trim() == "Connect with Swinburne University"

        and: "Should have Swinburne brand logo"
        assert $(selector + " #embedsource_AP3MMJNLO > img").attr("src").contains("swinburne-logo.svg")
        takeScreenshot($(selectorContainer).firstElement(), "Should have Swinburne Global")

        where:
        viewport << getViewPorts()
    }
}
