package specs.component.lists.eventlist

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class EventListScreenshotSpec extends ComponentSpec {

    String pathPage = "component/lists/event-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/eventlist"

    def setupSpec() {
        loginAsAdmin()
    }


    @Unroll("Appearance of Component with Default variant and Badge: Default in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Default"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist1"

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


    @Unroll("Appearance of Component with Default variant and Badge: Icon in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Icon"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist2"

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


    @Unroll("Appearance of Component with Default variant and Badge: Card with Icon in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Icon"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist3"

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


    @Unroll("Appearance of Component with Default variant and Badge: Card with Icon, Title and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Icon, Title and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist4"

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


    @Unroll("Appearance of Component with Default variant and Badge: Card with Icon, Title, Category, Description and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Icon, Title, Category, Description and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist5"

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


    @Unroll("Appearance of Component with Default variant and Badge: Card with Icon, Title and Date in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Icon, Title and Date"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist6"

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


    @Unroll("Appearance of Component with Default variant and Badge: Card with Icon, Title, Description and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Icon, Title, Description and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist7"

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



    @Unroll("Appearance of Component with Default variant and Badge: Card with Icon, Title, Subtitle and Date in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Icon, Title, Subtitle and Date"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist8"

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

    @Unroll("Appearance of Component with Default variant and Badge: Card with Icon, Title, Subtitle, Date, Description and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Icon, Title, Subtitle, Date, Description and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist9"

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

    @Unroll("Appearance of Component with Default variant and Badge: Card with Icon, Title, Subtitle, Description and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Icon, Title, Subtitle, Description and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist10"

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


    @Unroll("Appearance of Component with Default variant and Badge: Card with Image, Title and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Image, Title and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist11"

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



    @Unroll("Appearance of Component with Default variant and Badge: Card with Image, Title, Category, Description and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Image, Title, Category, Description and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist12"

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


    @Unroll("Appearance of Component with Default variant and Badge: Card with Image, Title, Description and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Image, Title, Description and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist13"

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

    @Unroll("Appearance of Component with Default variant and Badge: Card with Image, Title, Subtitle, Description and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Image, Title, Subtitle, Description and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist14"

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

    @Unroll("Appearance of Component with Default variant and Badge: Card with Title, Description and Action in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Title, Description and Action"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist15"

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

    @Unroll("Appearance of Component with Default variant and Badge: Clickable Card with Icon, Title, Category and Description in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Clickable Card with Icon, Title, Category and Description"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist16"

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


    @Unroll("Appearance of Component with Default variant and Badge: Clickable Card with Image and Title in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Clickable Card with Image and Title"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist17"

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


    @Unroll("Appearance of Component with Default variant and Badge: Clickable Card with Image, Title, Category and Description in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Clickable Card with Image, Title, Category and Description"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist18"

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

    @Unroll("Appearance of Component with Default variant and Badge: Horizontal Card with Icon, Title, Category and Description in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Horizontal Card with Icon, Title, Category and Description"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist19"

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


    @Unroll("Appearance of Component with Default variant and Badge: Card with Icon, Title, Subtitle, Date, Description and Action with Styles in #viewport.label")
    def "Appearance of Component with Default variant and Badge: Card with Icon, Title, Subtitle, Date, Description and Action with Styles"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#eventlist20"

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
