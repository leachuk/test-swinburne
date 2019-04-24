package specs.component.content.text

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class TextPublishSpec extends ComponentSpec {

    String pathPage = "component/content/text"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/text"


    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component Variant: Default in #viewport.label")
    def "Functionality of Component Variant: Default"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Text"'
        def selector = "#text1"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample rich text"
        assert $(selector).text().trim().startsWith("HEADING 1")
        report("Should have sample rich text")

        and: "Has sample paraformat-h1"
        assert $("${selector} h1").isEmpty() == false

        and: "Has sample paraformat-h2"
        assert $("${selector} h2").isEmpty() == false

        and: "Has sample paraformat-h3"
        assert $("${selector} h3").isEmpty() == false

        and: "Has sample paraformat-p"
        assert $("${selector} p").isEmpty() == false

        and: "Has sample paraformat-hyperlinks"
        assert $("${selector} a").isEmpty() == false

        and: "Has sample paraformat-ul"
        assert $("${selector} ul").isEmpty() == false

        and: "Has sample paraformat-ol"
        assert $("${selector} ol").isEmpty() == false

        and: "Has sample paraformat-small"
        assert $("${selector} p small").isEmpty() == false

        and: "Has sample table content"
        assert $("${selector} table").isEmpty() == false

        and: "Should have simple BR element"
        assert $("${selector} div br").isEmpty() == false

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component Variant: Simple in #viewport.label")
    def "Functionality of Component Variant: Simple"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Text"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#text2"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have sample rich text"
        assert $(selector).text().trim().startsWith("HEADING 1")
        report("Should have sample rich text")

        and: "Has sample paraformat-h1"
        assert $("${selector} h1").isEmpty() == false

        and: "Has sample paraformat-h2"
        assert $("${selector} h2").isEmpty() == false

        and: "Has sample paraformat-h3"
        assert $("${selector} h3").isEmpty() == false

        and: "Has sample paraformat-p"
        assert $("${selector} p").isEmpty() == false

        and: "Has sample paraformat-hyperlinks"
        assert $("${selector} a").isEmpty() == false

        and: "Has sample paraformat-ul"
        assert $("${selector} ul").isEmpty() == false

        and: "Has sample paraformat-ol"
        assert $("${selector} ol").isEmpty() == false

        and: "Has sample paraformat-small"
        assert $("${selector} p small").isEmpty() == false

        and: "Has sample table content"
        assert $("${selector} table").isEmpty() == false

        and: "Should have simple BR element"
        assert $("${selector} div br").isEmpty() == false

        where:
        viewport << getViewPorts()
    }

    @Unroll("Functionality of Component with visibility modifiers in #viewport.label")
    def "Functionality of Component with visibility modifiers"() {

        given: '>the page hierarchy is created as "Components" > "Content" > "Text"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_N6YNER6OF"
        def text_selector = "#contentblock_N6YNER6OF > div.content > div.contents > div"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)

        and: "Should have the have the INVISIBLE visibility modifier"
        assert $("${text_selector} > #text_2024348315_ZV7KBJ10M").attr("class").contains("invisible")

        and: "Should have the have the INVISIBLE XS DOWN visibility modifier"
        assert $("${text_selector} > #text_1234007482_T7OXLJUXT").attr("class").contains("invisible-xs-down")

        and: "Should have the have the INVISIBLE MD DOWN visibility modifier"
        assert $("${text_selector} > #text_240127259_5MGBGFXJQ").attr("class").contains("invisible-md-down")

        and: "Should have the have the INVISIBLE LG DOWN visibility modifier"
        assert $("${text_selector} > #text_1128533466_NLE985MBZ").attr("class").contains("invisible-lg-down")

        and: "Should have the have the INVISIBLE XL DOWN visibility modifier"
        assert $("${text_selector} > #text_1454555955_AP7GQHTVI").attr("class").contains("invisible-xl-down")

        and: "Should have the have the INVISIBLE XS UP visibility modifier"
        assert $("${text_selector} > #text_1933564755_B056LWXEZ").attr("class").contains("invisible-xs-up")

        and: "Should have the have the INVISIBLE SM UP visibility modifier"
        assert $("${text_selector} > #text_1519611596_XM7NN80B4").attr("class").contains("invisible-sm-up")

        and: "Should have the have the INVISIBLE MD UP visibility modifier"
        assert $("${text_selector} > #text_1465670839_4G05NC960").attr("class").contains("invisible-md-up")

        and: "Should have the have the INVISIBLE LG UP visibility modifier"
        assert $("${text_selector} > #text_1007097376_FZYJASS43").attr("class").contains("invisible-lg-up")

        and: "Should have the have the INVISIBLE XL UP visibility modifier"
        assert $("${text_selector} > #text_1462394320_6UY7LGFW5").attr("class").contains("invisible-xl-up")



        where:
        viewport << getViewPorts()
    }
}
