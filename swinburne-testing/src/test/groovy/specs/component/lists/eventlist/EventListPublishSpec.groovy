package specs.component.lists.eventlist


import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class EventListPublishSpec extends ComponentSpec {

    String pathPage = "component/lists/event-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/eventlist"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Event List: Default variant using Badge: Default in #viewport.label")
    def "Event List: Default variant using Badge: Default"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist1"
        def selectorContainer = "#contentblock1 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Event List: Default variant using Badge: Icon in #viewport.label")
    def "Event List: Default variant using Badge: Icon"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist2"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Event List: Default variant using Badge: Card with Icon in #viewport.label")
    def "Event List: Default variant using Badge: Card with Icon"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist3"
        def selectorContainer = "#contentblock3 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }


    @Unroll("Event List: Default variant using Badge: Card with Icon, Title and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Icon, Title and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist4"
        def selectorContainer = "#contentblock4 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }


    @Unroll("Event List: Default variant using Badge: Card with Icon, Title, Category, Description and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Icon, Title, Category, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist5"
        def selectorContainer = "#contentblock5 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }



    @Unroll("Event List: Default variant using Badge: Card with Icon, Title and Date in #viewport.label")
    def "Event List: Default variant using Badge: Card with Icon, Title and Date"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist6"
        def selectorContainer = "#contentblock6 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }



    @Unroll("Event List: Default variant using Badge: Card with Icon, Title, Description and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Icon, Title, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist7"
        def selectorContainer = "#contentblock7 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }


    @Unroll("Event List: Default variant using Badge: Card with Icon, Title, Subtitle and Date in #viewport.label")
    def "Event List: Default variant using Badge: Card with Icon, Title, Subtitle and Date"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist8"
        def selectorContainer = "#contentblock8 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }


    @Unroll("Event List: Default variant using Badge: Card with Icon, Title, Subtitle, Date, Description and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Icon, Title, Subtitle, Date, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist9"
        def selectorContainer = "#contentblock9 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }


    @Unroll("Event List: Default variant using Badge: Card with Icon, Title, Subtitle, Description and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Icon, Title, Subtitle, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist10"
        def selectorContainer = "#contentblock10 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Event List: Default variant using Badge: Card with Image, Title and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Image, Title and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist11"
        def selectorContainer = "#contentblock11 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Event List: Default variant using Badge: Card with Image, Title, Category, Description and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Image, Title, Category, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist12"
        def selectorContainer = "#contentblock12 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }



    @Unroll("Event List: Default variant using Badge: Card with Image, Title, Description and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Image, Title, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist13"
        def selectorContainer = "#contentblock13 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Event List: Default variant using Badge: Card with Image, Title, Subtitle, Description and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Image, Title, Subtitle, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist14"
        def selectorContainer = "#contentblock14 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }


    @Unroll("Event List: Default variant using Badge: Card with Title, Description and Action in #viewport.label")
    def "Event List: Default variant using Badge: Card with Title, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist15"
        def selectorContainer = "#contentblock15 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Event List: Default variant using Badge: Clickable Card with Icon, Title, Category and Description in #viewport.label")
    def "Event List: Default variant using Badge: Clickable Card with Icon, Title, Category and Description"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist16"
        def selectorContainer = "#contentblock16 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Event List: Default variant using Badge: Clickable Card with Image and Title in #viewport.label")
    def "Event List: Default variant using Badge: Clickable Card with Image and Title"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist17"
        def selectorContainer = "#contentblock17 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Event List: Default variant using Badge: Clickable Card with Image, Title, Category and Description in #viewport.label")
    def "Event List: Default variant using Badge: Clickable Card with Image, Title, Category and Description"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist18"
        def selectorContainer = "#contentblock18 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }

    @Unroll("Event List: Default variant using Badge: Horizontal Card with Icon, Title, Category and Description in #viewport.label")
    def "Event List: Default variant using Badge: Horizontal Card with Icon, Title, Category and Description"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist19"
        def selectorContainer = "#contentblock19 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }


    @Unroll("Event List: Default variant using Badge: Card with Icon, Title, Subtitle, Date, Description and Action with Styles in #viewport.label")
    def "Event List: Default variant using Badge: Card with Icon, Title, Subtitle, Date, Description and Action with Styles"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Event List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist20"
        def selectorContainer = "#contentblock20 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        where:
        viewport << getViewPorts()
    }





}
