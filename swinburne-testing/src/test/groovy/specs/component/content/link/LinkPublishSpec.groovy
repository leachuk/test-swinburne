package specs.component.content.link

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class LinkPublishSpec extends ComponentSpec {

    String pathPage = "component/content/link"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/link"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {

        given: 'Page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component link"
        assert $(selector).text().trim() == "Link: Default"
        report("Should have sample component link")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Variant: Button in #viewport.label")
    def "Functionality of Component Variant: Button"() {

        given: 'Page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link2"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component link"
        assert $(selector).text().trim() == "Button"
        report("Should have sample component link")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Variant: Default no Label in #viewport.label")
    def "Functionality of Component Variant: Default no Label"() {

        given: 'The page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link3"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component link"
        assert $(selector).text().trim() == "Link"
        report("Should have sample component link")

        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component Variant: Default with Analytics in #viewport.label")
    def "Functionality of Component Variant: Default with Analytics"() {

        given: 'The page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link6"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component text"
        assert $(selector).text().trim() == "Link"
        report("Should have sample component text")

        and: "Should have analytics attribute: data-analytics-event"
        assert $(selector).attr("data-analytics-event") == "site interaction"

        and: "Should have analytics attribute: data-layer-type"
        assert $(selector).attr("data-layer-track") == "true"

        and: "Should have analytics attribute: data-layer-location"
        assert $(selector).attr("data-layer-location") == "link6-location"

        and: "Should have analytics attribute: data-layer-label"
        assert $(selector).attr("data-layer-label") == "link-label"


        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component Variant: Button with Analytics in #viewport.label")
    def "Functionality of Component Variant: Button with Analytics"() {

        given: 'The page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link7"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component text"
        assert $(selector).text().trim() == "Link"
        report("Should have sample component text")

        and: "Should have analytics attribute: data-analytics-event"
        assert $(selector).attr("data-analytics-event") == "site interaction"

        and: "Should have analytics attribute: data-layer-type"
        assert $(selector).attr("data-layer-track") == "true"

        and: "Should have analytics attribute: data-layer-location"
        assert $(selector).attr("data-layer-location") == "link7-location"

        and: "Should have analytics attribute: data-layer-label"
        assert $(selector).attr("data-layer-label") == "link-label"


        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component Variant: Button with icon on left in #viewport.label")
    def "Functionality of Component Variant: Button with icon on left"() {

        given: 'The page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link8"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component text"
        assert $(selector).text().trim() == "Link"
        report("Should have sample component text")

        and: "Should have class: link button"
        assert $(selector).attr("class") == "link button"

        and: "Should have icon: fa fa-cog"
        assert $(selector + " .icon").attr("class").endsWith("fa fa-cog")

        and: "The icon should be the first element"
        assert $(selector + " :first-child").attr("class").startsWith("icon")

        where:
        viewport << getViewPorts()
    }


    @Unroll("Functionality of Component Variant: Button with icon on right in #viewport.label")
    def "Functionality of Component Variant: Button with icon on right"() {

        given: 'The page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link9"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component text"
        assert $(selector).text().trim() == "Link"
        report("Should have sample component text")

        and: "Should have class: link button"
        assert $(selector).attr("class") == "link button"

        and: "Should have icon: fa fa-cog"
        assert $(selector + " .icon").attr("class").endsWith("fa fa-cog")

        and: "The icon should be the last element"
        assert $(selector + " :last-child").attr("class").startsWith("icon")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Variant: Default without Analytics tracking false in #viewport.label")
    def "Functionality of Component Variant: Default without Analytics tracking false"() {

        given: 'The page hierarchy is created as "Components" > "Content" > "Link"'
        def selector = "#link10"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample component text"
        assert $(selector).text().trim() == "Link"
        report("Should have sample component text")

        and: "Should have analytics attribute: data-layer-track as false"
        assert $(selector).attr("data-layer-track") == "false"

        where:
        viewport << getViewPorts()
    }


}
